import {createRouter, createWebHashHistory} from "vue-router";

export const menus = [
    {
        name: 'Dashboard',
        path: '/dashboard',
        meta: {
            title: '首页',
            icon: 'BarChartOutlined'
        },
        component: () => import('@/views/dashboard.vue')
    },
    {
        name: 'User',
        path: '/user',
        meta: {
            title: '业务员管理',
            icon: 'UserOutlined'
        },
        component: () => import('@/views/user/index.vue')
    },
    {
        name: 'GoodsCategory',
        path: '/goodsCategory',
        meta: {
            title: '商品类别管理',
            icon: 'AppstoreOutlined'
        },
        component: () => import('@/views/goodsCategory/index.vue')
    },
    {
        name: 'Supplier',
        path: '/supplier',
        meta: {
            title: '供应商管理',
            icon: 'PartitionOutlined'
        },
        component: () => import('@/views/supplier/index.vue')
    },
    {
        name: 'Customer',
        path: '/customer',
        meta: {
            title: '客户管理',
            icon: 'UsergroupAddOutlined'
        },
        component: () => import('@/views/customer/index.vue')
    },
    {
        name: 'Repository',
        path: '/repository',
        meta: {
            title: '仓库管理',
            icon: 'CodeSandboxOutlined'
        },
        component: () => import('@/views/repository/index.vue')
    },
    {
        name: 'Goods',
        path: '/goods',
        meta: {
            title: '商品管理',
            icon: 'SketchOutlined'
        },
        component: () => import('@/views/goods/index.vue')
    },
    {
        name: 'Purchase',
        path: '/purchase',
        meta: {
            title: '入库管理',
            icon: 'SwapRightOutlined'
        },
        component: () => import('@/views/purchase/index.vue')
    },
    {
        name: 'Sale',
        path: '/sale',
        meta: {
            title: '出库管理',
            icon: 'SwapLeftOutlined'
        },
        component: () => import('@/views/sale/index.vue')
    },
    {
        name: 'Transfer',
        path: '/transfer',
        meta: {
            title: '转仓记录',
            icon: 'RetweetOutlined'
        },
        component: () => import('@/views/transfer/index.vue')
    },
    {
        name: 'Log',
        path: '/log',
        meta: {
            title: '操作日志',
            icon: 'DeploymentUnitOutlined'
        },
        component: () => import('@/views/log/index.vue')
    },
    // AI助手测试页面
    {
        name: 'AiAssistantTest',
        path: '/ai-assistant-test',
        meta: {
            title: 'AI助手测试',
            icon: 'RobotOutlined'
        },
        component: () => import('@/views/AiAssistantTest.vue')
    }
]

const routes = [
    {
        name: 'Login',
        path: '/login',
        meta: {
            title: '登录'
        },
        component: () => import('@/views/Login.vue')
    },
    {
        name: 'Index',
        path: '/',
        component: () => import('@/views/index.vue'),
        redirect: '/dashboard',
        children: menus
    }
]

const router = createRouter({
    routes: routes,
    history: createWebHashHistory()
});

router.beforeEach((to, from, next) => {
    const token = localStorage.getItem('token')
    if(token) {
        if(to.path === '/login') {
            next('/')
        } else {
            next()
        }
    } else {
        if(to.path === '/login') {
            next()
        } else {
            next('/login')
        }
    }
});

export default router