<template>
    <div class="wrapper">
        <div style="margin: 200px auto;background-color: #fff;width: 350px;height: 300px;padding: 20px;border-radius: 10px">
            <div style="margin: 20px 0;text-align: center;font-size: 24px"><b>登录</b>

               <el-form :model="userView" :rules="rules" ref="userForm">
                   <el-form-item  prop="username">
                <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="userView.username"></el-input>
                   </el-form-item>
                   <el-form-item prop="password">
                       <el-input  size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="userView.password"></el-input>
                   </el-form-item>
                <div style="margin: 10px 0;text-align: right">
                    <el-button type="primary" size="small" autocomplete="off" @click="login">登录</el-button>
                    <el-button type="primary" size="small" autocomplete="off" @click="$router.push('/Register')">注册</el-button>

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
                ]
            }
        }
    },
    methods:{
        login(){
            this.$refs['userForm'].validate((valid) => {
                if (valid) {//表单校验合法
                    this.request.post("/user/login",this.userView).then(res=>{
                        if(res.code==='200'){
                            localStorage.setItem("userView",JSON.stringify(res.data))//存储用户信息到浏览器
                            this.$router.push("/")
                            this.$message.success("登录成功");
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