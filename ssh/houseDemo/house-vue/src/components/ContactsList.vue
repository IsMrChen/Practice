<template>
    <el-table
            :data="tableData"
            style="width: 90%"
            max-height="250">
        <el-table-column
                fixed
                prop="dob"
                label="创建日期"
                width="150">
        </el-table-column>
        <el-table-column
                prop="name"
                label="姓名"
                width="120">
        </el-table-column>
        <el-table-column
                prop="gender"
                label="姓别"
                width="120">
        </el-table-column>
        <el-table-column
                prop="address"
                label="地址"
                width="120">
        </el-table-column>
        <el-table-column
                prop="email"
                label="邮箱"
                width="120">
        </el-table-column>
        <el-table-column
                prop="mobile"
                label="电话"
                width="120">
        </el-table-column>
        <el-table-column
                fixed="right"
                label="操作"
                width="120">
            <template slot-scope="scope">
                <el-button
                        @click.native.prevent="deleteRow(scope.$index, tableData)"
                        type="text"
                        size="small">
                    移除
                </el-button>
            </template>
        </el-table-column>
    </el-table>
</template>

<script>

    export default {
        methods: {
            deleteRow(index, rows) {
                rows.splice(index, 1);
            },

        },
        created(){
            let token=this.$cookies.get("token");
            const _this=this;
            axios.get('http://127.0.0.1:8888/findAllUsers',
                {
                    headers:{
                        token:token
                    }
                }
            ).then(function (resp) {
                _this.tableData=resp.data;
            })
        },
        data() {
            return {
                tableData: [{
                    dob: '',
                    name: '',
                    gender: '',
                    email: '',
                    address: '',
                    mobile: 0
                }]
            }
        }
    }
</script>
<style>
</style>