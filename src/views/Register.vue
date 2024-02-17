<template>
    <div class="wrapper">
        <div style="margin: 100px auto;background-color: #fff;width: 350px;height: 400px;padding: 20px;border-radius: 10px">
            <div style="margin: 20px 0;text-align: center;font-size: 24px"><b>注册</b>

               <el-form :model="userView" :rules="rules" ref="userForm">
                   <el-form-item  prop="username">
                <el-input placeholder="请输入账号" size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="userView.username"></el-input>
                   </el-form-item>
                   <el-form-item prop="password">
                       <el-input placeholder="请输入密码"  size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="userView.password"></el-input>
                   </el-form-item>

                   <el-form-item prop="Confirmpassword">
                       <el-input placeholder="请确认密码"  size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="userView.Confirmpassword"></el-input>
                   </el-form-item>


                <div style="margin: 10px 0;text-align: right">
                    <el-button type="primary" size="small" autocomplete="off" @click="register">确定</el-button>
                    <el-button type="primary" size="small" autocomplete="off" @click="$router.push('/LoginView')">返回登录</el-button>

                </div>
               </el-form>


            </div>

        </div>

    </div>
</template>

<script>

export default {
    name: "LoginView",

    data(){

        return {
            userView: {},
            rules: {
                username: [
                    {required: true, message: '请输入用户名称', trigger: 'blur'},
                    {min: 3, max: 10, message: '长度在 3 到 10个字符', trigger: 'blur'}
                ],
                password: [
                    {required: true, message: '请输入密码', trigger: 'blur'},
                    {min: 6, max: 20, message: '长度在 6到 20 个字符', trigger: 'blur'}
                ],
                Confirmpassword: [
                    {required: true, message: '请输入密码', trigger: 'blur'},
                    {min: 6, max: 20, message: '长度在 6到 20 个字符', trigger: 'blur'}
                ]
            }
        }
    },
    methods:{
        register(){
            this.$refs['userForm'].validate((valid) => {
                if (valid) {//表单校验合法
                    this.request.post("/user/register",this.userView).then(res=>{
                        if(res.code==='200'){

                            this.$message.success("注册成功");
                        } else{
                            this.$message.error(res.msg)
                        }
                    })
                }
            });



        }
    }


}
</script>


<style scoped>

.wrapper {
    height: 100vh;
    background-image: linear-gradient(to bottom right, #FC466B, #3F5EFB);
    overflow: hidden;
}

</style>