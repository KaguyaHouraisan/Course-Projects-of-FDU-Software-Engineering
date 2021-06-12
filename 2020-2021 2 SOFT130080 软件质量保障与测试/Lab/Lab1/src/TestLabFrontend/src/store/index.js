import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        token: localStorage.getItem('token') || null,
        userDetails: localStorage.getItem('userDetails') || null,
        account: localStorage.getItem('account') || null,
        product: localStorage.getItem('product') || null,
        loan:localStorage.getItem('loan') || null
    },
    mutations: {
        login(state, data){
            localStorage.setItem('token', data.token)
            //localStorage.setItem('userDetails', data.userDetails)
            //state.user = data.userDetails
            state.token = data.token
        },
        setAccount(state, data){
            localStorage.setItem('account', data)
            state.account = data

        },
        setProduct(state, data){
            localStorage.setItem('product', data)
            state.product = data
        },
        setLoan(state, data){
            localStorage.setItem('loan', data)
            state.loan = data
        },
        logout(state) {
            // 移除token
            localStorage.removeItem('token')
            localStorage.removeItem('userDetails')
            localStorage.removeItem('userInformation')
            localStorage.removeItem('userGroup')
            state.userDetails = null
            state.token = null
        },

        // tempStorage2(state,data){
        //     localStorage.removeItem('tempStorage2')
        //     localStorage.setItem('tempStorage2', data)
        //     state.tempStorage2=data
        // }
    },
    getters:{
        getUserDetails: state => state.userDetails,
        getToken: state => state.token,
        getAccount: state => state.account,
        getProduct: state => state.product,
        getLoan: state => state.loan,
        // getTempStorage2: state => state.tempStorage2
    },
    actions: {
    }
})
