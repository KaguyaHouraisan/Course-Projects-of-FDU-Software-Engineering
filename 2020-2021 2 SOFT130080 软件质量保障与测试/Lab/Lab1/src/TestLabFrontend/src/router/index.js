import Vue from 'vue'
import HelloWorld from "./../components/HelloWorld";
import Router from 'vue-router'
import Login from './../components/Login'
import Homepage from './../components/Homepage'
import store from './../store'
import loanDetails from "./../components/LoanDetails";
import manageLoan from "./../components/ManageLoan";
import dailyFinal from "./../components/DailyFinal";
import checkWMP from "./../components/CheckWMP"
import transaction from "./../components/Transaction"
import purchaseWMP from "./../components/PurchaseWMP"
import WMP from "./../components/WMP"
import checkCustomer from "./../components/CheckCustomer"
Vue.use(Router);

export const router = new Router({
    routes: [
        {
            path: '/',
            name: 'HelloWorld',
            component: HelloWorld,
            meta: {
                cannotBeAccessed: true//跳转到homepage
            }
        },
        {
            path: '/login',
            name: 'Login',
            component: Login,
            meta: {//       requireAuth: false
            }
        },
        {
            path: '/homepage',
            name: 'Homepage',
            component: Homepage,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/loanDetails',
            name: 'LoanDetails',
            component: loanDetails,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/manageLoan',
            name: 'ManageLoan',
            component: manageLoan,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/dailyFinal',
            name: 'dailyFinal',
            component: dailyFinal,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/checkWMP',
            name: 'checkWMP',
            component: checkWMP,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/transaction',
            name: 'transaction',
            component: transaction,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/purchaseWMP',
            name: 'purchaseWMP',
            component: purchaseWMP,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/checkCustomer',
            name: 'checkCustomer',
            component:checkCustomer,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/WMP',
            name: 'WMP',
            component: WMP,
            meta: {
                requireAuth: true
            }
        },
   ]
});

router.beforeEach((to, from, next) => {

    console.log(to)
    if (to.path!="/login"){
        if (store.getters.getToken!=null){
            next();
        }else{
            console.log(1111111)
            next("/login")
        }
    }
    next();

    /*
    if (to.meta.requireAuth) {  // 判断该路由是否需要登录权限
        if (store.state.token) {  // 通过vuex state获取当前的token是否存在
            next();
        }
        else {
            console.log(store.state.token);
            next({
                path: '/login',
                // query: {redirect: to.fullPath} // 登录成功之后重新跳转到该路由
            })
        }
    }
    else {
        next();
    }
    if (to.matched.some(record => record.meta.cannotBeAccessed)) {
        next({
            path: '/homepage',
        })
    }*/
});
