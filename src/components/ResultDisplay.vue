<!--  -->
<template>
	<div class="result">
		<div class="search">
			<!-- 搜索框 -->
			<div class="search_in">
				<el-input
					placeholder="请输入内容"
					v-model="keyword"
					class="searchClass"
					clearable
                    @keyup.enter.native="search"
				></el-input>
				<el-button @click="search" type="primary" icon="el-icon-search"></el-button>
			</div>
			<!-- 搜做结果 -->
			<div class="result_items">
                <div v-show="resultsSize == 0">
                    <h2>未查询到结果</h2>
                </div>
				<div class="result_item" v-for="(result,index) in results" :key="index">
					<div class="item_title">
						<a v-bind:href="result.articleUrl" target="_blank">
							<h3>{{ result.articleTitle }}</h3>
						</a>
					</div>
					<div class="item_desc">
						<span class="f">
							{{ result.articleDate }}-
							<span>
								{{ result.description }}
							</span>
						</span>
					</div>
				</div>

			</div>
			<!-- 分页 -->
			<div class="pageNum">
				<el-pagination
					background
					layout="prev, pager, next"
					:total="100"
                    :current-page="currPage"
                    class="el-page"
                    @current-change="pageChange"
                    @next-click="nextClick"
                    @prev-click="prevClick"
				></el-pagination>
			</div>
		</div>
	</div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';

export default {
	name: "ResultDisplay",
	//import引入的组件需要注入到对象中才能使用
	components: {},
	data() {
		//这里存放数据
		return {
			keyword: "",
            results: [], // 搜索的结果，后端返回
            currPage:1,
            resultsSize:10
		}
    },
    created() {
        console.log(this);
        this.getParams();
        console.log(this.keyword);
        this.$axios.get('/search/' + this.keyword + "/" + this.currPage).then(response=>{
                console.log(response);
                this.results=response.data;
                this.resultsSize=this.results.length;
                console.log(this.resultsSize);
                console.log(this.results);
            })
    },
    methods:{
        getParams(){
            console.log(this.$route.query);
            var key = this.$route.query.keyword;
            console.log(key);
            this.keyword = key;
        },
        search() {
            this.$axios.get('/search/' + this.keyword + "/" + this.currPage).then(response=>{
                console.log(response);
                this.results=response.data;
                this.resultsSize=this.results.length;
                console.log(this.results);
            })
            console.log("搜索");
        },
        pageChange(page) {
            console.log(page);
            this.currPage = page;
            this.$axios.get('/search/' + this.keyword + "/" + this.currPage).then(response=>{
                console.log(response);
                this.results=response.data;
                this.resultsSize=this.results.length;
                console.log(this.results);
            })
            console.log("页面更改");
        },
        nextClick() {
            this.currPage = this.currPage + 1;
            console.log(this.currPage);
            this.$axios.get('/search/' + this.keyword + "/" + this.currPage).then(response=>{
                console.log(response);
                this.results=response.data;
                this.resultsSize=this.results.length;
                console.log(this.results);
            })
            console.log("页面更改");
        },
        prevClick() {
            this.currPage = this.currPage - 1;
            console.log(this.currPage);
            this.$axios.get('/search/' + this.keyword + "/" + this.currPage).then(response=>{
                console.log(response);
                this.results=response.data;
                this.resultsSize=this.results.length;
                console.log(this.results);
            })
            console.log("页面更改");
        }
    }
}
</script>

<style>
/* // scoped表示旨在本文件内起作用，局部声明 */

/* //@import url(); 引入公共css类 */
.search {
	margin: auto 0;
	position: relative;
}
.search .search_in {
    margin-top: 30px;
	width: 100%;
	text-align: center;
}
.search .search_in .searchClass {
	width: 50%;
	border-radius: 20px;
}
.search .search_in button {
	border-radius: 5px;
}

/* 搜索结果开始 */
.result_items {
    width: 100%;
    height: 600px;
	margin-top: 30px;
	text-align: center;
	position: relative;
}
.result_items .result_item {
	margin-top: 20px;
	display: inline-block;
	/* margin: auto 0; */
	width: 60%;
	border: 1px;
	/* 阴影效果 */
	box-shadow: 0 0 30px 3px rgba(119, 118, 118, 0.33);
	height: 120px;
}

/* 结果题目 */
.result_items .result_item .item_title {
	text-align: left;
	margin-left: 10px;
	margin-top: 2px;
}

.result_items .result_item .item_title h3 {
	margin-top: 5px;
	margin-bottom: 5px;
}

/* 结果描述 */
.result_items .result_item .item_desc {
	height: 60px;
	text-align: left;
	margin-left: 10px;
	margin-top: 10px;
	margin-bottom: 10px;
}

.result_items .result_item .item_desc .f {
	display: inline-block;
	width: 100%;
	height: 100%;
	word-wrap: break-word;
	word-break: break-all;
	overflow: hidden;
	white-space: normal;
	text-overflow: ellipsis;
}

/* .result_items .result_item .item_desc .f>span {
    display: inline-block;
    width: 100%;
    word-wrap: break-word;
    word-break: break-all;
    overflow: hidden;
    white-space: normal;
    text-overflow: ellipsis;
} */

/* 搜索结果结束 */

/* 分页 */

.search .pageNum {
    margin-bottom: 10px;
    position: fixed;
    bottom: 10px;
    text-align: center;
    left: 0;
    right: 0;
}

.search .pageNum .el-page {
    display: inline-block;
}

a {
	text-decoration: none;
	color: #008c8c;
}
/* a:link {
    color: #008c8c;
} */
a:hover {
	text-decoration: #008c8c;
	text-decoration: underline;
}
/* a:visited {
    color: #609;
} */
</style>
