import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
// 这里定义初始值
let state = {
  resultData:{},//存储查询结果
};

const mutations = {
  setResultData(state,resultData){
    state.resultData = resultData;
  },

};

// 事件触发后的逻辑操作
// 参数为事件函数
const actions = {

};

// 返回改变后的数值
const getters = {
  getResultData: state => state.resultData
};

export default new Vuex.Store({
  state,
  mutations,
  actions,
  getters,
})
