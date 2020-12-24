var  hello = new Vue({
    el:"#hello",
    data:{
        activeModule:"upload",
        activeIndex:"1",
        file:{
            name:""
        },
        resultPath:"",
        fileList:[{
            name:"",
            remoteRelPath:"",
            size:"",
            type:"",
            localRelPath:"",
            md5:"",
            id:""
        }],
        multiple:false
    },
    methods:{
        switchTo(s){
          this.activeModule = s;
          if (s==='all'){
              this.getAll()
          }
        },
        addFile(){
            let c = document.getElementById("fileinput");
            c.click()
        },
        tirggerFile(event) {
            var file = event.target.files; // (利用console.log输出看file文件对象)
            console.log(file.length)
            this.file = file[0]
        },
        submitPost(){
            if (!this.file.size){
                this.$message.error({
                    message:"选择文件再提交。懂？",
                    duration:1500
                })
                return
            }
            let formData = new FormData();
            formData.append("file",this.file)
            fetch("/api/add/gitee",{
                method:"POST",
                body:formData
            }).then(res=>{
                res.json().then(data=>{
                    this.resultPath = data.content
                })
            }).catch(err=>{
                console.log(err)
            })
        },
        getAll(){
            fetch("http://localhost:9991/api/all",{
                method:"GET"
            }).then(res=>{
                res.json().then(data=>{
                    this.fileList = data.content
                })
            }).catch(err=>{
                console.log(err)
            })
        }
    }
})