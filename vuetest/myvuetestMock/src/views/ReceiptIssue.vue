<template>
    <div>
        <el-steps :active="active" finish-status="success">
            <el-step title="选择收据类型"></el-step>
            <el-step title="填写收据信息"></el-step>
            <el-step title="开具完成"></el-step>
        </el-steps>
        <el-card class="box-card"  shadow="hover" v-show="dialogType">
                <div slot="header" class="clearfix">
                    <span>选择收据类型</span>
                    <el-button style="float: right; padding: 3px 0" type="text"  @click="next">下一步</el-button>
                </div>
                <div class="text item">
                    <el-form :model="ruleForm1" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                        <el-form-item label="收据类型">
                            <el-select v-model="Form.type" placeholder="收据类型">
                                <el-option v-for="(item,index) in typeMap" :label="item.name" :value="item.name">{{item.name}}</el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="支付方式" prop="payment">
                            <el-select v-model="Form.payment" placeholder="请选择活动区域">
                                <el-option label="现金支付" value="现金支付"></el-option>
                                <el-option label="微信支付" value="微信支付"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-form>
                </div>
        </el-card>
        <el-card class="box-card"  shadow="hover" v-show="dialogInfo">
            <div slot="header" class="clearfix">
                <span>填写收据信息</span>
                <el-button style="float: right; padding: 3px 0" type="text"  @click="next">下一步</el-button>
                <el-button style="float: right; padding: 3px 0" type="text"  @click="back">上一步</el-button>
            </div>
            <div class="text item">
                <el-form :model="Form" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="付款单位" prop="unit">
                        <el-input v-model="Form.unit"></el-input>
                    </el-form-item>
                    <el-form-item label="收据描述" prop="detail">
                        <el-input v-model="Form.detail"></el-input>
                    </el-form-item>
                    <el-form-item label="收据金额" prop="price">
                        <el-input v-model="Form.price"></el-input>
                    </el-form-item>
                    <el-form-item label="客户号" prop="customerId">
                        <el-input v-model="Form.customerId"></el-input>
                    </el-form-item>

                </el-form>
            </div>
        </el-card>
        <el-card class="box-card"  shadow="hover" v-show="dialogIssue">
            <div slot="header" class="clearfix">
                <span>开具信息</span>
                <el-button style="float: right; padding: 3px 0" type="text"  @click="mysuccess">完成</el-button>
                <el-button style="float: right; padding: 3px 0" type="text"  @click="back">上一步</el-button>
            </div>
            <div class="text item">
                <table class="mytable">
                    <tr>
                        <td>收据编号</td>
                        <td>{{Form.code}}</td>
                    </tr>
                    <tr>
                        <td>收据类型</td>
                        <td>{{Form.type}}</td>
                    </tr>
                    <tr>
                        <td>付款单位</td>
                        <td>{{Form.unit}}</td>
                    </tr>
                    <tr>
                        <td>收据描述</td>
                        <td>{{Form.detail}}</td>
                    </tr>
                    <tr>
                        <td>收据金额</td>
                        <td>{{Form.price}}</td>
                    </tr>
                    <tr>
                        <td>支付方式</td>
                        <td>{{Form.payment}}</td>
                    </tr>
                    <tr>
                        <td>客户号</td>
                        <td>{{Form.customerId}}</td>
                    </tr>
                </table>
            </div>
        </el-card>
        <el-card class="box-card"  shadow="hover" v-show="success">
            <div slot="header" class="clearfix">
                <span>完成</span>
                <i class="el-icon-circle-check" :active="active"></i>
                <el-button style="float: right; padding: 3px 0" type="text"  @click="again">继续开具</el-button>
            </div>
            <el-steps :space="200" :active="1" finish-status="success" style="margin-left: 45%;width: 50px ;height: 50px">
                <el-step title="已完成"></el-step>
            </el-steps>
        </el-card>
    </div>
</template>
<script>
    import MyTodoPage from "./MyTodoPage";
    export default {
        data() {
            return {
                dialogType:true,
                dialogInfo:false,
                dialogIssue:false,
                success:false,
                active: 0,
                ruleForm1: {
                    code: '',
                    payment: '',
                },
                ruleForm2:{
                    id:0,
                    unit:'',
                    detail:'',
                    price:'',
                    customerId:''
                },
                Form:{
                    id:0,
                    code:null,
                    type:null,
                    price:null,
                    payment:null,
                    subDate:'',
                    state:null,
                    address:'',
                    customerId:null
                },
                rules: {
                    code: [
                        { required: true, message: '请输入收据类型', trigger: 'blur' },
                        { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
                    ],
                    payment: [
                        { required: true, message: '请选择支付方式', trigger: 'blur' }
                    ],
                    unit: [
                        { required: true, message: '请输入付款单位', trigger: 'blur'}
                    ],
                    price: [
                        { required: true, message: '请输入收据金额', trigger: 'blur'}
                    ],
                    customerId: [
                        { required: true, message: '请输入客户号', trigger: 'blur'},
                    ],
                },
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
            };
        },
        methods: {
            back(){
                this.active--;
                switch (this.active) {
                    case 0 : this.dialogInfo=false;this.dialogType=true;break;
                    case 1 : this.dialogIssue=false;this.dialogInfo=true;break;
                    case 2 : this.dialogIssue=true;break;
                }
            },
            next() {
                //if (this.active++ > 2) this.active = 0;
                this.active++;
                switch (this.active) {
                    case 0 :  break;
                    case 1 : this.dialogType=false; this.dialogInfo=true;break;
                    case 2 : this.dialogInfo=false;this.dialogIssue=true;break;
                }
                if(this.active==2){
                    var id = '';
                    //设置长度，这里看需求，我这里设置了4
                    var codeLength = 10;
                    //设置随机字符
                    var random = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
                    //循环codeLength 我设置的4就是循环4次
                    for (var i = 0; i < codeLength; i++) {
                        //设置随机数范围,这设置为0 ~ 36
                        var index = Math.floor(Math.random() * 9);
                        //字符串拼接 将每次随机的字符 进行拼接
                        id += random[index];
                    }
                    this.Form.code=id;
                }
            },
            mysuccess(){
                this.active++;
                this.success=true;
                this.dialogIssue=false;
                const _this=this;
                axios.post('/user/add',this.Form).then(function (resp) {
                    console.log(resp);
                })
            },
            again(){
               this.active=0;
               this.success=false;
               this.dialogType=true;
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        alert('submit!');
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        },
    }
</script>


<style scoped>
 .el-steps{
     width: 80%;
     margin-left: 10%;
 }
 .text {
     font-size: 14px;
 }

 .item {
     margin-bottom: 18px;
 }

 .clearfix:before,
 .clearfix:after {
     display: table;
     content: "";
 }
 .clearfix:after {
     clear: both
 }

 .box-card {
     width: 60%;
     margin-top: 20px;
     margin-left: 20%;
 }
 .mytable {
     border:1px solid #A6C1E4;

     font-family:Arial;

     border-collapse: collapse;
     width: 80%;
     height: 100%;
     margin-left: 10%;

 }
 table td{
     border:1px solid #a5a5a5;
     text-align:center;
     height:20px;
     padding-top:5px;
     font-size:15px;
     background-color: #f8f8f8;

 }

</style>