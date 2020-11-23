<template>
  <div class="">
    <Header></Header>

    <div class="block container">
      <el-timeline>
        <el-timeline-item :timestamp="blog.created" placement="top" v-for="(blog,index) in blogs">
          <el-card>
            <h4>
              <!--<router-link :to="/blog/+blog.id">-->
              <router-link :to="{name: 'BlogDetail', params: {id: blog.id}}">
                {{ blog.title }}
              </router-link>
            </h4>
            <p>{{ blog.description }}</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
      <el-row :gutter="20">
        <el-col :span="12" :offset="6">
          <div class="grid-content bg-purple">
            <el-pagination
                background
                layout="prev, pager, next"
                :current-page="current"
                :page-size="size"
                :total="total"
                @current-change="page">
            </el-pagination>
          </div>
        </el-col>
      </el-row>

    </div>
  </div>

</template>

<script>
import Header from "@/components/Header";

export default {
  name: "Blogs",
  components: {
    Header
  },
  data() {
    return {
      blogs: [],
      current: 1,
      total: 0,
      size: 5,
      pages: 1
    }
  },
  methods: {
    page: function (current) {
      const _this = this
      _this.$axios.get('blogs?current=' + current).then(response => {
        _this.blogs = response.data.data.records
        _this.total = response.data.data.total
        _this.current = response.data.data.current
        _this.pages = response.data.data.pages
        _this.size = response.data.data.size
      })
    }
  },
  created() {
    this.page(1)
  }
}
</script>

<style scoped lang="less">
.container {
  margin: 0 200px;
}

.el-row {
  margin-bottom: 20px;

  & :last-child {
    margin-bottom: 0;
  }

}

.el-col {
  border-radius: 4px;
}

.bg-purple-dark {
  //background: #99a9bf;
}

.bg-purple {
  //background: #d3dce6;
}

.bg-purple-light {
  //background: #e5e9f2;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>