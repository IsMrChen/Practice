<template>
    <div>
            <el-form :inline="true" :model="formInline" ref="formInline" class="demo-form-inline" id="formInline">
                <el-form-item label="收据编号">
                    <el-input v-model="formInline.code" placeholder="收据编号"></el-input>
                </el-form-item>
                <el-form-item label="收据类型">
                    <el-select v-model="formInline.type" placeholder="收据类型">
                        <el-option v-for="(item,index) in typeMap" :key="item.id" :label="item.name" :value="item.id">{{item.name}}</el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="收据状态">
                    <el-select v-model="formInline.state" placeholder="收据状态">
                        <el-option v-for="(item,index) in stateMap" :label="item.name" :value="item.id">{{item.name}}</el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="支付方式">
                    <el-select v-model="formInline.payment" placeholder="支付方式">
                        <el-option v-for="(item,index) in payMap" :label="item.name" :value="item.id">{{item.name}}</el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <span class="demonstration">支付时间</span>
                    <el-date-picker
                            v-model="formInline.date"
                            type="daterange"
                            range-separator="至"
                            start-placeholder="开始月份"
                            end-placeholder="结束月份"
                            value-format="yyyy-MM-dd">
                    </el-date-picker>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit">查询</el-button>
                    <el-button @click="reset()"><i class="el-icon-refresh-right"></i>重置</el-button>
                </el-form-item>
            </el-form>
            <el-table
                    :data="tableData"
                    border
                    style="width: 100%">
                <el-table-column
                        fixed
                        prop="id"
                        label="序号"
                        width="50">
                </el-table-column>
                <el-table-column
                        prop="code"
                        label="收据编号"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="type"
                        label="收据类型"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="price"
                        label="收据金额"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="payment"
                        label="付款方式"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="subDate"
                        label="提交时间"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="unit"
                        label="付款单位"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="payDate"
                        label="支付时间"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="state"
                        label="收据状态"
                        width="120">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="100px">
                    <template slot-scope="scope">
                        <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                        <el-button @click="deleteRow(scope.row)" type="text" size="small">移除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        <el-pagination
                background
                layout="total,prev, pager, next"
                :total="total"
                :pageSize="5"
                @current-change="page">
        </el-pagination>
        <el-dialog title="收货地址" :visible.sync="dialogTableVisible">
            <el-divider><i class="el-icon-location"></i></el-divider>
            <el-form>
                <el-form-item>收据编号：{{gridData.code}}</el-form-item>
                <el-form-item>支付时间：{{gridData.date}}</el-form-item>
                <el-form-item>付款单位：{{gridData.name}}</el-form-item>
                <el-form-item>收货地址：{{gridData.address}}</el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        methods: {
            deleteRow(rows){
                console.log(rows);
                const _this=this;
                axios.post('/user/delete',rows).then(function (resp) {
                    alert(resp.data.data.message);
                    _this.onSubmit(function (tesp) {
                        resp.data.data.map(function (val) {
                            _this.typeMap.map(function(t){
                                if(val.type==t.id){
                                    val.type=t.name;
                                }
                            })
                            _this.payMap.map(function (p) {
                                if(val.payment==p.id){
                                    val.payment=p.name;
                                }
                            })
                            _this.stateMap.map(function (s) {
                                if(val.state==s.id){
                                    val.state=s.name;
                                }
                            })
                        })
                        _this.tableData=resp.data.data
                        _this.total=resp.data.count;
                        console.log(resp)
                    })
                })
                /*axios.post('/user/getUser',this.formInline).then(function (resp) {

                })*/
            },
            handleClick(row) {
                this.dialogTableVisible=true;
                this.gridData.code=row.code;
                this.gridData.date=row.payDate;
                this.gridData.name=row.unit;
                this.gridData.address=row.address;
            },
            onSubmit() {
                console.log('submit!');
                const _this=this
                this.formInline.page=this.listQuery.page;
                axios.post('/user/getUser',this.formInline).then(function (resp) {
                    resp.data.data.map(function (val) {
                        _this.typeMap.map(function(t){
                            if(val.type==t.id){
                                val.type=t.name;
                            }
                        })
                        _this.payMap.map(function (p) {
                            if(val.payment==p.id){
                                val.payment=p.name;
                            }
                        })
                        _this.stateMap.map(function (s) {
                            if(val.state==s.id){
                                val.state=s.name;
                            }
                        })
                    })
                    _this.tableData=resp.data.data
                    _this.total=resp.data.count;
                    console.log(resp)
                })
            },
            reset(){
                this.formInline.code='';
                this.formInline.type='';
                this.formInline.state='';
                this.formInline.payment='';
                this.formInline.date='';
            },
            page(currentPage){
                const _this=this
                this.formInline.page=currentPage
                axios.post('/user/getUser',this.formInline).then(function (resp) {
                    resp.data.data.map(function (val) {
                        _this.typeMap.map(function(t){
                            if(val.type==t.id){
                                val.type=t.name;
                            }
                        })
                        _this.payMap.map(function (p) {
                            if(val.payment==p.id){
                                val.payment=p.name;
                            }
                        })
                        _this.stateMap.map(function (s) {
                            if(val.state==s.id){
                                val.state=s.name;
                            }
                        })
                    })
                    _this.tableData=resp.data.data
                    _this.total=resp.data.count;
                    console.log(resp)
                })
            }
        },
        created() {
            const _this=this
            // api就是你线上的网址
            axios.post('/user/getUser', this.formInline).then(function (resp) {
                resp.data.data.map(function (val) {
                    _this.typeMap.map(function(t){
                        if(val.type==t.id){
                            val.type=t.name;
                        }
                    })
                    _this.payMap.map(function (p) {
                        if(val.payment==p.id){
                            val.payment=p.name;
                        }
                    })
                    _this.stateMap.map(function (s) {
                        if(val.state==s.id){
                            val.state=s.name;
                        }
                    })
                })
                _this.tableData=resp.data.data
                _this.total=resp.data.count;
                console.log(resp)
            }).catch((err) => {
                console.log(err)
            });
        },
        data() {
            return {
                dialogTableVisible:false,
                currentPage:1,
                total:100,
                tableData: [{
                    id:'1',
                    code:'0949',
                    type:1,
                    price:'60000',
                    payment:1,
                    subDate:'2020-10-01',
                    unit:'王超',
                    payDate:'2020-10-01',
                    state:1,
                    address:''
                }],
                typeMap:[
                    {
                        id:1,
                        name:'搬运设备'
                    },
                    {
                        id:2,
                        name:'员工付款'
                    },
                    {
                        id:3,
                        name:'客饭费'
                    },
                    {
                        id:4,
                        name:'梯货款'
                    },
                    {
                        id:5,
                        name:'不合格品赔偿'
                    },
                    {
                        id:6,
                        name:'罚款'
                    },
                    {
                        id:7,
                        name:'其他'
                    },
                ],
                payMap:[
                    {
                        id:1,
                        name:'现金支付'
                    },
                    {
                        id:2,
                        name:'微信支付'
                    },

                ],
                stateMap:[
                    {
                        id:1,
                        name:'已完成'
                    },
                    {
                        id:2,
                        name:'待付款'
                    },
                    {
                        id:3,
                        name:'未转账'
                    },
                    {
                        id:4,
                        name:'已退回'
                    },
                    {
                        id:5,
                        name:'已作废'
                    },

                ],
                formInline: {
                    code: '',
                    type: '',
                    state:'',
                    payment:'',
                    date:'',
                    page:1,
                    limit: 5,
                },
                gridData: {
                    code:'',
                    date:'',
                    name:'',
                    address:'',
                },
                listQuery: {
                    page: 1,
                    limit: 5
                }

            }
        }
    }
</script>
