// vue.config.js
module.exports = {
    // 选项...
    devServer: {
        // Paths
        proxy: {
          '/api':{
                target:'http://127.0.0.1:8083',
                changeOrigin:true,
                pathRewrite:{
                    '^/api':''
            }
          }
        }
    }
}