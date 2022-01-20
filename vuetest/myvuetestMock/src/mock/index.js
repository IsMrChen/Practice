import Mock from 'mockjs'
//将mock数据文件引入
import tableAPI from './demo/List'

Mock.mock('/user/getUser', 'post', tableAPI.getUserList)
Mock.mock('/user/add', 'post', tableAPI.getUserAdd)
Mock.mock('/user/delete', 'post', tableAPI.getUserDelete)
export default Mock

