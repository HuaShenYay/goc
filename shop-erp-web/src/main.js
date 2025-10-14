import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import Antd from 'ant-design-vue';
import * as antdIcons from '@ant-design/icons-vue'
import 'ant-design-vue/dist/reset.css';
import router from "@/router/index.js";

const app = createApp(App)
Object.keys(antdIcons).forEach(key=> {
    app.component(key, antdIcons[key])
})
app.use(Antd).use(router).mount('#app')