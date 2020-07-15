import Vue from "vue";
import App from "./App.vue";
// 引入路由
import router from "./router" // 会自动扫描router路径下的index.js文件，vue中.js就是配置文件

// 按需引入ElementUI和样式
import { Input,Button,Select,Option,Pagination} from "element-ui"
import 'element-ui/lib/theme-chalk/index.css';
import axios from "axios"

Vue.use(Input);
Vue.use(Button);
Vue.use(Select);
Vue.use(Option);
Vue.use(Pagination);
Vue.use(axios);

Vue.config.productionTip = false;
// 设置全局axios
Vue.prototype.$axios=axios;
// 设置基本的默认路径
axios.defaults.baseURL='/api';

new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
