(this.webpackJsonpfile_management_system=this.webpackJsonpfile_management_system||[]).push([[0],{44:function(e,a,t){e.exports=t(78)},49:function(e,a,t){},54:function(e,a,t){},78:function(e,a,t){"use strict";t.r(a);var n=t(0),r=t.n(n),l=t(22),s=t.n(l),o=(t(49),t(3)),i=t(4),c=t(6),m=t(7),u=t(5),d=t(2),p=t(18),f=t(14),v=t(41),E=t(17),b={files:[],file:{}},h=function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:b,a=arguments.length>1?arguments[1]:void 0;switch(a.type){case"GET_FILES":return Object(E.a)({},e,{files:a.payload});case"DELETE_FILE":return Object(E.a)({},e,{files:e.files.filter((function(e){return e.id!==a.payload}))});default:return e}},g={errors:[]},y=function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:g,a=arguments.length>1?arguments[1]:void 0;switch(a.type){case"GET_ERRORS":return Object(E.a)({},e,{errors:a.payload});default:return e}},N={user:{},valid:!1},O=function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:N,a=arguments.length>1?arguments[1]:void 0;switch(a.type){case"SIGN_IN":return Object(E.a)({},e,{user:a.payload,valid:!!a.payload});case"SIGN_OUT":return Object(E.a)({},e,{user:a.payload,valid:!1});default:return e}},w=Object(f.c)({fileReduxStore:h,errorReduxStore:y,userReduxStore:O}),k={},C=[v.a],S=window.__REDUX_DEVTOOLS_EXTENSION__&&window.__REDUX_DEVTOOLS_EXTENSION__(),j=window.navigator.userAgent.includes("Chrome")&&S?Object(f.e)(w,k,Object(f.d)(f.a.apply(void 0,C),S)):Object(f.e)(w,k,Object(f.d)(f.a.apply(void 0,C))),R=(t(54),t(55),t(1)),x=t(8),_=t.n(x),F=t(15),D=t(12),T=t.n(D),U=t(42),G=t.n(U),I=function(e){Object(m.a)(t,e);var a=Object(c.a)(t);function t(e){var n;return Object(o.a)(this,t),(n=a.call(this,e)).state={valid:!1},n.onClick=n.onClick.bind(Object(R.a)(n)),n}return Object(i.a)(t,[{key:"componentWillReceiveProps",value:function(e){this.setState({valid:e.myValid})}},{key:"onClick",value:function(e){this.props.signOut()}},{key:"render",value:function(){return r.a.createElement("nav",{className:"navbar navbar-expand-sm navbar-dark bg-primary mb-4"},r.a.createElement("div",{className:"container"},this.state.valid&&r.a.createElement("div",{className:"collapse navbar-collapse navbar-brand",id:"mobile-nav"},r.a.createElement("ul",{className:"navbar-nav mr-auto"},r.a.createElement("li",{className:"nav-item"},r.a.createElement(d.b,{className:"nav-link",to:"/dashboard"},"Welcome ",this.props.myUserName))),r.a.createElement("ul",{className:"navbar-nav mr-auto"},r.a.createElement("li",{className:"nav-item"},r.a.createElement(d.b,{onClick:this.onClick,to:"/",className:"btn btn-sm btn-warning"},"Sign Out"))))))}}]),t}(n.Component),A=Object(u.b)((function(e){return{myUserName:e.userReduxStore.user.fullname,myValid:e.userReduxStore.valid}}),{signOut:function(){return function(e){T.a.defaults.headers.common.Authorization="",localStorage.removeItem("jwt"),e({type:"SIGN_OUT",payload:{}})}}})(I),L=(t(38),t(21)),P=t.n(L),V=function(e){Object(m.a)(t,e);var a=Object(c.a)(t);function t(e){var n;return Object(o.a)(this,t),(n=a.call(this,e)).onUpdateClick=n.onUpdateClick.bind(Object(R.a)(n)),n.onDeleteClick=n.onDeleteClick.bind(Object(R.a)(n)),n.onDownloadClick=n.onDownloadClick.bind(Object(R.a)(n)),n}return Object(i.a)(t,[{key:"onUpdateClick",value:function(e){var a=this;P.a.prompt("Insert new name: ",(function(e){var t=new FormData;t.append("id",a.props.file.id),t.append("filename",e),a.props.updateFile(t,a.props.update)}))}},{key:"onDownloadClick",value:function(e){this.props.downloadFile(this.props.file.id,this.props.file.filename)}},{key:"onDeleteClick",value:function(e){var a=this;P.a.confirm("Are you sure you want to delete this file?",(function(e){e&&a.props.deleteFile(a.props.file.id)}))}},{key:"render",value:function(){var e=this.props.file;return r.a.createElement("div",{className:"container"},r.a.createElement("div",{className:"card card-body bg-light mb-3"},r.a.createElement("div",{className:"row"},r.a.createElement("div",{className:"col-2"},r.a.createElement("span",{className:"mx-auto"},"Serial Number",r.a.createElement("br",null),e.serial)),r.a.createElement("div",{className:"col-lg-6 col-md-4 col-8"},r.a.createElement("h3",null,e.filename),r.a.createElement("p",null,e.description),r.a.createElement("p",null,r.a.createElement("strong",null,"Upload Date: "),e.uploadDate)),r.a.createElement("div",{className:"col-md-4 d-none d-lg-block"},r.a.createElement("ul",{className:"list-group"},r.a.createElement(d.b,{to:"/dashboard",onClick:this.onDownloadClick},r.a.createElement("li",{className:"list-group-item update"},r.a.createElement("i",{className:"fas fa-file-download"}," Download"))),r.a.createElement(d.b,{to:"/dashboard",onClick:this.onUpdateClick},r.a.createElement("li",{className:"list-group-item update"},r.a.createElement("i",{className:"far fa-window-restore"}," Rename File Name"))),r.a.createElement("li",{className:"list-group-item delete",onClick:this.onDeleteClick},r.a.createElement("i",{className:"fa fa-minus-circle pr-1"}," Delete")))))),r.a.createElement("hr",null))}}]),t}(n.Component),W=Object(u.b)(null,{deleteFile:function(e){return function(){var a=Object(F.a)(_.a.mark((function a(t){return _.a.wrap((function(a){for(;;)switch(a.prev=a.next){case 0:return a.prev=0,a.next=3,T.a.delete("/api/files/".concat(e));case 3:t({type:"DELETE_FILE",payload:e}),t({type:"GET_ERRORS",payload:{}}),a.next=10;break;case 7:a.prev=7,a.t0=a.catch(0),t({type:"GET_ERRORS",payload:a.t0.response.data});case 10:case"end":return a.stop()}}),a,null,[[0,7]])})));return function(e){return a.apply(this,arguments)}}()},downloadFile:function(e,a){return function(){var t=Object(F.a)(_.a.mark((function t(n){var r,l,s,o;return _.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.prev=0,t.next=3,T.a.get("/api/files/download/".concat(e));case 3:r=t.sent,l=window.URL.createObjectURL(new Blob([r.data])),(s=document.createElement("a")).href=l,o=a,s.setAttribute("download",o),document.body.appendChild(s),s.click(),s.remove(),n({type:"GET_ERRORS",payload:{}}),t.next=18;break;case 15:t.prev=15,t.t0=t.catch(0),n({type:"GET_ERRORS",payload:t.t0.response.data});case 18:case"end":return t.stop()}}),t,null,[[0,15]])})));return function(e){return t.apply(this,arguments)}}()},updateFile:function(e,a){return function(){var t=Object(F.a)(_.a.mark((function t(n){return _.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.prev=0,t.next=3,T.a.post("/api/files/update",e);case 3:a(),n({type:"GET_ERRORS",payload:{}}),t.next=10;break;case 7:t.prev=7,t.t0=t.catch(0),n({type:"GET_ERRORS",payload:t.t0.response.data});case 10:case"end":return t.stop()}}),t,null,[[0,7]])})));return function(e){return t.apply(this,arguments)}}()}})(V),M=function(e){Object(m.a)(t,e);var a=Object(c.a)(t);function t(e){var n;return Object(o.a)(this,t),(n=a.call(this,e)).state={errors:!1},n.updateComp=n.updateComp.bind(Object(R.a)(n)),n}return Object(i.a)(t,[{key:"componentWillReceiveProps",value:function(e){e.myValid&&this.setState({errors:e.myValid}),e.myErrors.accessDenied&&(P.a.alert({message:e.myErrors.accessDenied,className:"rubberBand animated alert alert-danger text-center"}),this.props.clearErrors())}},{key:"componentDidMount",value:function(e){this.props.getAllFiles()}},{key:"updateComp",value:function(){this.props.getAllFiles()}},{key:"render",value:function(){var e=this,a=this.props,t=a.myFiles,n=a.myValid;return r.a.createElement("div",{className:"projects"},r.a.createElement("div",{className:"container"},r.a.createElement("div",{className:"row"},r.a.createElement("div",{className:"col-md-12"},n&&r.a.createElement(d.b,{to:"/addFile",className:"btn btn-lg btn-info"},"Upload"),r.a.createElement("br",null),r.a.createElement("hr",null),r.a.createElement("hr",null),!n&&r.a.createElement("div",{className:"alert alert-danger text-center"},r.a.createElement("strong",null,"Unauthorized access..."),r.a.createElement("br",null),"Please log in to use the File Management Tool!",r.a.createElement("br",null),r.a.createElement(d.b,{to:"/signin",className:"btn btn-lg btn-info"},"Login")),0===t.length&&n&&r.a.createElement("div",{className:"alert alert-success text-center"},"Welcome to your Personal File Management Tool...",r.a.createElement("br",null),"Press ",r.a.createElement("strong",null,"Upload")," to start uploading files!",r.a.createElement("br",null)),t&&t.map((function(a){return r.a.createElement(W,{update:e.updateComp,key:a.id,file:a})})),r.a.createElement("br",null),r.a.createElement("hr",null),r.a.createElement("hr",null)))))}}]),t}(n.Component),z=Object(u.b)((function(e){return{myFiles:e.fileReduxStore.files,myValid:e.userReduxStore.valid,myErrors:e.errorReduxStore.errors}}),{clearErrors:function(){return function(e){e({type:"GET_ERRORS",payload:{}})}},getAllFiles:function(){return function(){var e=Object(F.a)(_.a.mark((function e(a){var t;return _.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,T.a.get("/api/files");case 3:t=e.sent,a({type:"GET_FILES",payload:t.data}),a({type:"GET_ERRORS",payload:{}}),e.next=11;break;case 8:e.prev=8,e.t0=e.catch(0),a({type:"GET_ERRORS",payload:e.t0.response.data});case 11:case"end":return e.stop()}}),e,null,[[0,8]])})));return function(a){return e.apply(this,arguments)}}()}})(M),B=t(16),X=t(11),J=t.n(X),q=function(e){Object(m.a)(t,e);var a=Object(c.a)(t);function t(e){var n;return Object(o.a)(this,t),(n=a.call(this,e)).state={file:null,filename:"",description:"",errors:{}},n.onFileChange=n.onFileChange.bind(Object(R.a)(n)),n.onChange=n.onChange.bind(Object(R.a)(n)),n.onSubmit=n.onSubmit.bind(Object(R.a)(n)),n}return Object(i.a)(t,[{key:"componentWillReceiveProps",value:function(e){e.myErrors&&this.setState({errors:e.myErrors})}},{key:"onFileChange",value:function(e){e.preventDefault(),this.setState({file:e.target.files[0],filename:e.target.files[0].name})}},{key:"onChange",value:function(e){this.setState(Object(B.a)({},e.target.name,e.target.value))}},{key:"onSubmit",value:function(e){e.preventDefault();var a=new FormData;a.append("filename",this.state.filename),a.append("description",this.state.description),a.append("file",this.state.file),this.props.addFile(a,this.props.history)}},{key:"render",value:function(){var e=this.state.errors;return r.a.createElement("div",null,r.a.createElement("div",{className:"register"},r.a.createElement("div",{className:"container"},r.a.createElement("div",{className:"row"},r.a.createElement("div",{className:"col-md-8 m-auto"},r.a.createElement("h5",{className:"display-4 text-center"},"Upload a file"),e.file&&r.a.createElement("div",{className:"alert alert-danger text-center"},r.a.createElement("strong",null,e.file),r.a.createElement("br",null)),r.a.createElement("hr",null),r.a.createElement("form",{onSubmit:this.onSubmit},r.a.createElement("div",{className:"input-group"},r.a.createElement("div",{className:"custom-file"},r.a.createElement("input",{type:"file",accept:".txt,.pdf",className:J()("form-control form-control-lg",{"is-invalid":e.filename}),name:"file",onChange:this.onFileChange}),e.file&&r.a.createElement("div",{className:"invalid-feedback"},e.file))),r.a.createElement("hr",null),r.a.createElement("hr",null),r.a.createElement("div",{className:"form-group"},r.a.createElement("input",{type:"text",disabled:!0,className:J()("form-control form-control-lg",{"is-invalid":e.filename}),placeholder:"File Name",name:"filename",value:this.state.filename,onChange:this.onChange}),e.filename&&r.a.createElement("div",{className:"invalid-feedback"},e.filename)),r.a.createElement("div",{className:"form-group"},r.a.createElement("textarea",{className:J()("form-control form-control-lg",{"is-invalid":e.description}),placeholder:"File Description",name:"description",value:this.state.description,onChange:this.onChange}),e.description&&r.a.createElement("div",{className:"invalid-feedback"},e.description)),r.a.createElement("input",{type:"submit",value:"Upload",className:"btn btn-primary btn-block mt-4"})))))))}}]),t}(n.Component),$=Object(u.b)((function(e){return{myErrors:e.errorReduxStore.errors}}),{addFile:function(e,a){return function(){var t=Object(F.a)(_.a.mark((function t(n){return _.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.prev=0,t.next=3,T.a.post("/api/files",e);case 3:a.push("/dashboard"),n({type:"GET_ERRORS",payload:{}}),t.next=10;break;case 7:t.prev=7,t.t0=t.catch(0),n({type:"GET_ERRORS",payload:t.t0.response.data});case 10:case"end":return t.stop()}}),t,null,[[0,7]])})));return function(e){return t.apply(this,arguments)}}()}})(q),H=function(e){Object(m.a)(t,e);var a=Object(c.a)(t);function t(){return Object(o.a)(this,t),a.apply(this,arguments)}return Object(i.a)(t,[{key:"render",value:function(){return r.a.createElement("div",{className:"landing"},r.a.createElement("div",{className:"light-overlay landing-inner text-dark"},r.a.createElement("div",{className:"container"},r.a.createElement("div",{className:"row"},r.a.createElement("div",{className:"col-md-12 text-center"},r.a.createElement("h1",{className:"display-3 mb-4"},"File Management System"),r.a.createElement("p",{className:"lead"},"Please log in to start managing your files!"),r.a.createElement("hr",null),r.a.createElement(d.b,{to:"/signup",className:"btn btn-lg btn-primary mr-2"},"Sign Up"),r.a.createElement(d.b,{to:"/signin",className:"btn btn-lg btn-secondary mr-2"},"Login"))))))}}]),t}(n.Component),K=Object(u.b)(null,null)(H),Q=function(e){Object(m.a)(t,e);var a=Object(c.a)(t);function t(e){var n;return Object(o.a)(this,t),(n=a.call(this,e)).state={username:"",password:"",errors:{}},n.onChange=n.onChange.bind(Object(R.a)(n)),n.onSubmit=n.onSubmit.bind(Object(R.a)(n)),n}return Object(i.a)(t,[{key:"componentWillReceiveProps",value:function(e){console.log("RECIEVED",e),e.myError&&this.setState({errors:e.myError}),e.myValid&&this.props.history.push("/dashboard")}},{key:"onChange",value:function(e){this.setState(Object(B.a)({},e.target.name,e.target.value))}},{key:"onSubmit",value:function(e){e.preventDefault();var a={username:this.state.username,password:this.state.password};this.props.signIn(a)}},{key:"render",value:function(){var e=this.state.errors;return r.a.createElement("div",{className:"login"},r.a.createElement("div",{className:"container"},r.a.createElement("div",{className:"row"},r.a.createElement("div",{className:"col-md-8 m-auto"},r.a.createElement("h1",{className:"display-4 text-center"},"Log In"),e.username&&r.a.createElement("div",{className:"alert alert-danger text-center"},r.a.createElement("strong",null,"Sign up instead?"),r.a.createElement("br",null),r.a.createElement(d.b,{to:"/signup",className:"btn btn-lg btn-info"},"Sign Up")),r.a.createElement("form",{onSubmit:this.onSubmit},r.a.createElement("div",{className:"form-group"},r.a.createElement("input",{type:"email",className:J()("form-control form-control-lg",{"is-invalid":e.username}),placeholder:"Email Address",name:"username",value:this.state.username,onChange:this.onChange}),e.username&&r.a.createElement("div",{className:"invalid-feedback"},e.username)),r.a.createElement("div",{className:"form-group"},r.a.createElement("input",{type:"password",className:J()("form-control form-control-lg",{"is-invalid":e.password}),placeholder:"Password",name:"password",value:this.state.password,onChange:this.onChange}),e.password&&r.a.createElement("div",{className:"invalid-feedback"},e.password)),r.a.createElement("input",{type:"submit",className:"btn btn-info btn-block mt-4"}))))))}}]),t}(n.Component),Y=Object(u.b)((function(e){return{myUser:e.userReduxStore.user,myError:e.errorReduxStore.errors,myValid:e.userReduxStore.valid}}),{signIn:function(e){return function(){var a=Object(F.a)(_.a.mark((function a(t){var n,r,l,s,o;return _.a.wrap((function(a){for(;;)switch(a.prev=a.next){case 0:return a.prev=0,a.next=3,T.a.post("/api/users/signin",e);case 3:n=a.sent,r=n.data,l=r.valid,s=r.token,localStorage.setItem("jwt",s),l?T.a.defaults.headers.common.Authorization=s:delete T.a.defaults.headers.common.Authorization,o=G()(s),t({type:"SIGN_IN",payload:o}),t({type:"GET_ERRORS",payload:{}}),a.next=15;break;case 12:a.prev=12,a.t0=a.catch(0),t({type:"GET_ERRORS",payload:a.t0.response.data});case 15:case"end":return a.stop()}}),a,null,[[0,12]])})));return function(e){return a.apply(this,arguments)}}()}})(Q),Z=function(e){Object(m.a)(t,e);var a=Object(c.a)(t);function t(e){var n;return Object(o.a)(this,t),(n=a.call(this,e)).state={fullname:"",username:"",password:"",passwordConfirm:"",role:"",errors:{}},n.onChange=n.onChange.bind(Object(R.a)(n)),n.onSubmit=n.onSubmit.bind(Object(R.a)(n)),n}return Object(i.a)(t,[{key:"componentWillReceiveProps",value:function(e){e.myErrors&&this.setState({errors:e.myErrors})}},{key:"onChange",value:function(e){this.setState(Object(B.a)({},e.target.name,e.target.value))}},{key:"onSubmit",value:function(e){e.preventDefault();var a={fullname:this.state.fullname,username:this.state.username,password:this.state.password,role:this.state.role,passwordConfirm:this.state.passwordConfirm};this.props.signUp(a,this.props.history)}},{key:"render",value:function(){var e=this.state.errors;return r.a.createElement("div",{className:"register"},r.a.createElement("div",{className:"container"},r.a.createElement("div",{className:"row"},r.a.createElement("div",{className:"col-md-8 m-auto"},r.a.createElement("h1",{className:"display-4 text-center"},"Sign Up"),r.a.createElement("p",{className:"lead text-center"},"Create your Account"),e.username&&r.a.createElement("div",{className:"alert alert-danger text-center"},r.a.createElement("strong",null,"Sign in instead?"),r.a.createElement("br",null),r.a.createElement(d.b,{to:"/signin",className:"btn btn-lg btn-info"},"Sign in")),r.a.createElement("form",{onSubmit:this.onSubmit},r.a.createElement("div",{className:"form-group"},r.a.createElement("input",{type:"text",className:J()("form-control form-control-lg",{"is-invalid":e.fullname}),placeholder:"Name",name:"fullname",required:!0,value:this.state.fullname,onChange:this.onChange}),e.fullname&&r.a.createElement("div",{className:"invalid-feedback"},e.fullname)),r.a.createElement("div",{className:"form-group"},r.a.createElement("input",{type:"email",className:J()("form-control form-control-lg",{"is-invalid":e.username}),placeholder:"Email Address",name:"username",value:this.state.username,onChange:this.onChange}),e.username&&r.a.createElement("div",{className:"invalid-feedback"},e.username)),r.a.createElement("div",{className:"form-group"},r.a.createElement("input",{type:"password",className:J()("form-control form-control-lg",{"is-invalid":e.password}),placeholder:"Password",name:"password",value:this.state.password,onChange:this.onChange}),e.password&&r.a.createElement("div",{className:"invalid-feedback"},e.password)),r.a.createElement("div",{className:"form-group"},r.a.createElement("input",{type:"password",className:J()("form-control form-control-lg",{"is-invalid":e.passwordConfirm}),placeholder:"Confirm Password",name:"passwordConfirm",value:this.state.passwordConfirm,onChange:this.onChange}),e.passwordConfirm&&r.a.createElement("div",{className:"invalid-feedback"},e.passwordConfirm)),r.a.createElement("div",{className:"form-group"},r.a.createElement("select",{className:J()("form-control form-control-lg",{"is-invalid":e.role}),name:"role",value:this.state.role,onChange:this.onChange},r.a.createElement("option",{value:""},"Select Role"),r.a.createElement("option",{value:"ADMIN"},"Adminstrator"),r.a.createElement("option",{value:"USER"},"User")),e.role&&r.a.createElement("div",{className:"invalid-feedback"},e.role)),r.a.createElement("input",{type:"submit",className:"btn btn-info btn-block mt-4"}))))))}}]),t}(n.Component),ee=Object(u.b)((function(e){return{myErrors:e.errorReduxStore.errors}}),{signUp:function(e,a){return function(){var t=Object(F.a)(_.a.mark((function t(n){return _.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.prev=0,t.next=3,T.a.post("/api/users/signup",e);case 3:n({type:"GET_ERRORS",payload:{}}),a.push("/signin"),t.next=10;break;case 7:t.prev=7,t.t0=t.catch(0),n({type:"GET_ERRORS",payload:t.t0.response.data});case 10:case"end":return t.stop()}}),t,null,[[0,7]])})));return function(e){return t.apply(this,arguments)}}()}})(Z),ae=function(e){Object(m.a)(t,e);var a=Object(c.a)(t);function t(){return Object(o.a)(this,t),a.apply(this,arguments)}return Object(i.a)(t,[{key:"render",value:function(){return r.a.createElement(u.a,{store:j},r.a.createElement(d.a,null,r.a.createElement("div",{className:"App"},r.a.createElement(A,null),r.a.createElement(p.a,{path:"/",component:K,exact:!0}),r.a.createElement(p.a,{path:"/signin",component:Y,exact:!0}),r.a.createElement(p.a,{path:"/signup",component:ee,exact:!0}),r.a.createElement(p.a,{path:"/dashboard",component:z,exact:!0}),r.a.createElement(p.a,{path:"/addFile",component:$,exact:!0}))))}}]),t}(n.Component);Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));s.a.render(r.a.createElement(r.a.StrictMode,null,r.a.createElement(ae,null)),document.getElementById("root")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then((function(e){e.unregister()})).catch((function(e){console.error(e.message)}))}},[[44,1,2]]]);
//# sourceMappingURL=main.dae5b5eb.chunk.js.map