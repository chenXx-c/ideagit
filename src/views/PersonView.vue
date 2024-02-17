 <template>
     <el-card style="width: 500px">


      <el-form label-width="80px" size="small">

          <el-upload
                  class="avatar-uploader"
                  action="http://localhost:8081/file/upload"
                  :show-file-list="false"
                  :on-success="handleAvatarSuccess">
              <img v-if="form.avatar" :src="form.avatar" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>




          <el-form-item label="用户名" :label-width="formLabelWidth">
              <el-input v-model="form.username" autocomplete="off"></el-input>
          </el-form-item>

          <el-form-item label="昵称" :label-width="formLabelWidth">
              <el-input v-model="form.nickname" autocomplete="off"></el-input>
          </el-form-item>

          <el-form-item label="邮箱" :label-width="formLabelWidth">
              <el-input v-model="form.email" autocomplete="off"></el-input>
          </el-form-item>

          <el-form-item label="电话" :label-width="formLabelWidth">
              <el-input v-model="form.phone" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="地址" :label-width="formLabelWidth">
              <el-input v-model="form.address" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item>
              <el-button type="primary" @click="save">确定</el-button>
          </el-form-item>


      </el-form>
     </el-card>
  </template>

     <script>
        export default {

            data(){

                return{
                    form:{},
                    userView:localStorage.getItem("userView")?JSON.parse(localStorage.getItem("userView")):{}
                }
            },
            created() {
               this.getUser().then(res=>{
                   console.log(res)
                   this.form=res
               })
            },
            methods:{
              async  getUser(){
                return  (await this.request.get("/user/username/"+this.userView.username)).data
                },
                save() {
                    this.request.post("/user", this.form).then(res => {
                        if (res.code==='200') {
                            this.$message.success("保存成功")

                            this.$emit("refreshUser")
                            //更新浏览器存储的哦用户信息
                            this.getUser().then(res=>{
                                res.token=JSON.parse(localStorage.getItem("userView")).token
                                localStorage.setItem("userView",JSON.stringify(res))
                            })

                        } else {
                            this.$message.success("保存失败")
                        }
                    })

                },
                handleAvatarSuccess(res){
                    this.form.avatar=res
                }
            }

         }
     </script>


    <style >
    .avatar-uploader{
        text-align: center;
       padding-bottom: 10px;
    }
    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }
    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }
    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }
    .avatar {
        width: 138px;
        height: 138px;
        display: block;
    }
    </style>