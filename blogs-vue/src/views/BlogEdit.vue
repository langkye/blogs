<template>
  <div>
    <Header></Header>
    <div class="container">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="标题" prop="title">
          <el-input v-model="ruleForm.title"></el-input>
        </el-form-item>

        <el-form-item label="摘要" prop="description">
          <el-input type="textarea" v-model="ruleForm.description"></el-input>
        </el-form-item>

        <el-form-item label="内容" prop="content">
          <mavon-editor v-model="ruleForm.content"></mavon-editor>
        </el-form-item>


        <el-row type="flex" class="row-bg">
          <el-col :span="6"></el-col>
          <el-col :span="6"></el-col>
          <el-col :span="6"></el-col>
          <el-col :span="6">
            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')">发表</el-button>
              <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>


      </el-form>

    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";

export default {
  name: "BlogEdit",
  components: {
    Header
  },
  data() {
    return {
      ruleForm: {
        id: '',
        title: '',
        description: '',
        content: '',
      },
      rules: {
        title: [
          {required: true, message: '请输入活动名称', trigger: 'blur'},
          {min: 3, max: 25, message: '长度在 3 到 25 个字符', trigger: 'blur'}
        ],
        desc: [
          {required: true, message: '请填写活动形式', trigger: 'blur'}
        ],
        content: [
          {required: true, message: '请填写内容', trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this
          _this.$axios.post('/blog/edit', _this.ruleForm, {
            headers: {
              authorization: localStorage.getItem('token')
            }
          }).then(res => {
            console.log(res)
            _this.open()
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    open() {
      this.$alert('操作成功', '提示', {
        confirmButtonText: '确定',
        callback: action => {
          this.$router.push('/blogs')
        }
      });
    }
  },
  created() {
    const id = this.$route.params.id;
    if (id){
      const _this = this
      this.$axios.get(`/blog/${id}`).then(res=>{
        const blog = res.data.data
        _this.ruleForm.id = blog.id
        _this.ruleForm.title = blog.title
        _this.ruleForm.description = blog.description
        _this.ruleForm.content = blog.content
      })
    }
  }
}
</script>

<style scoped lang="less">
.el-row {
  margin-bottom: 20px;

  &:last-child {
    margin-bottom: 0;
  }
}

.el-col {
  border-radius: 4px;
}

.bg-purple-dark {
}

.bg-purple {
}

.bg-purple-light {
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.row-bg {
  padding: 10px 0;
}
</style>