<template>
  <div>
    <Header></Header>
    <div class="blog">
      <h2>{{blog.title}}</h2>
      <div>{{blog.description}}
      </div>
      <el-link icon="el-icon-edit">
        <router-link :to="{name: 'BlogEdit', params: {id: blog.id}}">编辑</router-link>
      </el-link>
      <el-divider></el-divider>
      <div v-html="blog.content" class="markdown-body content"></div>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import 'github-markdown-css/github-markdown.css'

export default {
  name: "BlogDetail",
  components: {
    Header
  },
  data(){
    return {
      blog: {
        id: '',
        title: '',
        description: '',
        content: ''
      }
    }
  },
  created() {
    const id= this.$route.params.id
    const _this = this
    _this.$axios.get(`/blog/${id}`).then(res=>{
      if (res.data){
        const blog = res.data.data
        _this.blog.id = blog.id
        _this.blog.title = blog.title
        _this.description = blog.description
        var MarkdownIt = require('markdown-it')
        var md = new MarkdownIt()
        _this.blog.content = md.render(blog.content)
      }
    })
  }
}
</script>

<style scoped lang="less">
.blog{
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  margin: 0 100px;
  padding: 20px 15px;
}
.content{
}
</style>