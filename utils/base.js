const base = {
    get() {
        return {
            url : "http://localhost:8080/springboottg274cp5/",
            name: "springboottg274cp5",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springboottg274cp5/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "教学材料检查助手的设计与实现"
        } 
    }
}
export default base
