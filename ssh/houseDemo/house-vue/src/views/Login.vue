<template>
    <div class="login">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>用户登录</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="register">注册</el-button>
            </div>
            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="用户名">
                    <el-input v-model="ruleForm.username"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="pass">
                    <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm()">登录</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script>
    import { mapMutations } from 'vuex'
    export default {
        data() {
            return {
                token:'',
                ruleForm: {
                    username:'',
                    password: '',
                },
                rules: {
                    username: [
                        { trigger: 'blur' }
                    ],
                    password: [
                        { trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
            submitForm() {
                const _this=this;
                axios.post('http://127.0.0.1:8888/act/login',this.ruleForm).then(function (resp) {
                    console.log(resp);
                    let data=resp.data;
                    if(resp.status==200){
                        console.log(resp.data.token);
                        //将请求返回的token和用户名存储起来
                        _this.$cookies.set("token",resp.data.token);
                        _this.$cookies.set("username",resp.data.username);
                        //localStorage.setItem("token",resp.data.token);
                        //sessionStorage.setItem("token",resp.data.token);
                        _this.$store.commit("set_token",resp.data.data);
                        _this.$router.push('/index');
                    }else{
                        _this.$router.push('/register');
                    }
                })

            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            register(){
                this.$router.push('/register');
            }
        }
    }
</script>

<style scoped>
    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }
    .clearfix:after {
        clear: both
    }

    .box-card {
        width: 480px;
        margin-left: 30%;
        margin-top: 10%;
    }
</style>