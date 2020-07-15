<template>
	<div class="search">
		<img alt="Vue logo" src="../assets/logo.png" />
		<div class="search_in">
			<el-input
				placeholder="请输入内容"
				v-model="keyword"
				class="searchClass"
                clearable
                @keyup.enter.native="search"
			>
			</el-input>
            <el-button @click.prevent="search" type="primary" icon="el-icon-search"></el-button>

		</div>
	</div>
</template>

<script>
// 引入组件

export default {
	name: "Search",
    data() {
        return{
            keyword:"",
            select:""
        }
    },
    methods:{
        search() {
            var word = this.keyword;
            console.log(word);
            this.$router.push({
                path:"/search",
                query:{
                    keyword:word
                }
            });
            // this.$axios.get('/search/' + this.keyword + "/1").then(response=>{
            //     console.log(response);
            // })
        }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
.search {
    margin: auto 0;
    position: relative;
}
.search .search_in{
    width: 100%;
    text-align:center;
}
.search .search_in .searchClass {
    width: 50%;
    border-radius: 20px;
}
.search .search_in button {
    border-radius: 5px;
}
</style>
