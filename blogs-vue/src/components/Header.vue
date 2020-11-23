<template>
  <div class="container">
    <div class="header">
      <div class="title">
        <h2 class="name">水墨社区</h2>
        <span class="des">——拼凑人生，悦享生活.</span>
      </div>
      <div class="block">
        <el-avatar shape="square" :size="50" :src="user.avatar"></el-avatar>
      </div>
      <span>{{ user.username }}</span>
      <div class="link-wrapper">
        <span> <el-link type="info" href="/blogs">主页</el-link></span>
        <el-divider direction="vertical"></el-divider>

        <span><el-link type="success" href="/blog/add">发表博客</el-link></span>
        <el-divider direction="vertical"></el-divider>

        <span v-show="!isLogin"><el-link type="primary" href="/login">登录</el-link></span>
        <span v-show="isLogin"><el-link type="danger" @click="logout">退出</el-link></span>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      user: {
        username: '请登录',
        avatar: 'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',
      },
      isLogin: false
    }
  },
  methods: {
    logout: function (){
      const _this = this
      _this.$axios.get('/logout',{
        headers: {
          authorization: localStorage.getItem('token')
        }
      }).then(response=>{})
      _this.$store.commit('REMOVE_INFO')
      _this.$router.push('/login')
    }
  },
  created() {
    if (this.$store.getters.getUser.username){
      this.user.username = this.$store.getters.getUser.username
      this.user.avatar = this.$store.getters.getUser.avatar
      this.isLogin = true
    }
  }
}
</script>

<style scoped>
.container .header{
  max-width: 960px;
  margin: 0 auto;
  text-align: center;
}
.container .header .title{
  position: relative;
}
.container .header .title .des{
  white-space: nowrap;
  font-size: smaller;
  font-style: italic;
  position: absolute;
  bottom: 0;
  margin-left: 60px;
  color: #888;
}
.link-wrapper{
  margin: 10px 0;
}


</style>