import Mock from 'mockjs'
import en from "element-ui/src/locale/lang/en";
function param2Obj(url) {
    const search = url.split('?')[1]
    if (!search) {
        return {}
    }
    return JSON.parse('{"' + decodeURIComponent(search).replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g, '":"') + '"}')
}

let List = []
const count = 100
for (let i = 0; i < count; i++) {
    List.push(Mock.mock({
        'id':i+1,
        'code|1000000000-9999999999':1,
        'type|1-7':1,
        'price|5000-100000':0,
        'payment|1-2':0,
        'subDate':'@date("yyyy-MM-dd")',
        'unit':"@CNAME",//随机生成中文姓名
        'payDate':'@date("yyyy-MM-dd")',
        'state|1-5':0,
        'address':'@city(true)'
    }))
}

export default {
    /**
     * 获取列表
     * @return {{code: number, count: number, data: *[]}}
     */
   getUserList: function (config) {
        let it=JSON.parse(config.body);
        const page=it.page;
        const limit=it.limit;
        const type=it.type;
        const state=it.state;
        const payment=it.payment;
        const payDate=it.date;

        const code=it.code;
        let typeQueryList=TypeQuery(List,type);
        let stateQueryList=StateQuery(typeQueryList,state);
        let paymentQueryList=PaymentQuery(stateQueryList,payment);
        let codeQueryList=CodeQuery(paymentQueryList,code);
        let payDateQueryList=[]
        if(payDate!=''){
            payDateQueryList=PayDateQuery(codeQueryList,new Date(payDate[0]),new Date(payDate[1]));
        }else{
            payDateQueryList=codeQueryList;
        }
        const pageList = payDateQueryList.filter((item, index) => index < limit * page && index >= limit * (page - 1))
        return {
            code: 20000,
            count: payDateQueryList.length,
            data: pageList
        }
    },
    getUserAdd: function (config) {
        let it=JSON.parse(config.body);
        List.push({
            id: List.length+1,
            code: it.code,
            type: it.type,
            price: it.price,
            payment: it.payment,
            subDate: Mock.Random.now('yyyy-MM-dd'),
            unit:it.unit,
            state:3,
            address:Mock.Random.city(true)
        })
        return {
            code: 20000,
            data: {
                message: '添加成功',

            }
        }
    },
    getUserDelete: function (config) {
        let it=JSON.parse(config.body);
        const id=it.id;
        List = List.filter(u => u.id !== id)
        console.log(List);
        return {
            code: 20000,
            data:{
                message: '删除成功',
            }
        }
   }
}
function CodeQuery(list,keyWord) {
    var arr = [];
    if(keyWord>0) {
        console.log("code===" + list[1].type)
        for (var i = 0; i < list.length; i++) {
            if (list[i].code == keyWord) {
                arr.push(list[i]);
            }
        }
        return arr;
    }else{
        return list;
    }
}
function TypeQuery(list,keyWord) {
    var arr = [];
    if(keyWord>0) {
        console.log("type===" + list[1].type)
        for (var i = 0; i < list.length; i++) {
            if (list[i].type == keyWord) {
                arr.push(list[i]);
            }
        }
        return arr;
    }else{
        return list;
    }
}
function StateQuery(list,keyWord) {
    var arr = [];
    if(keyWord>0) {
        console.log("state===" + list[1].state)
        for (var i = 0; i < list.length; i++) {
            if (list[i].state == keyWord) {
                arr.push(list[i]);
            }
        }
        return arr;
    }else{
        return list;
    }
}

function PaymentQuery(list,keyWord) {
    var arr = [];
    if(keyWord>0) {
        console.log("payment===" + list[1].payment)
        for (var i = 0; i < list.length; i++) {
            if (list[i].payment == keyWord) {
                arr.push(list[i]);
            }
        }
        return arr;
    }else{
        return list;
    }
}
function PayDateQuery(list,startDate,endDate) {
    var arr=[];
    for (var i = 0; i < list.length; i++) {
        if (new Date(list[i].payDate)>=startDate&&new Date(list[i].payDate)<=endDate) {
                arr.push(list[i]);
            }
        }
    return arr;
}