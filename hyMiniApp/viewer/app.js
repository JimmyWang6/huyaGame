import { UI } from '@hyext/hy-ui'
import React, { Component } from 'react'
import './app.hycss'
import ReactDOM from 'react-dom';
import ReconnectingWebSocket from "reconnecting-websocket";

const { View, Text } = UI
//定义 websocket connection
let options = {
   maxRetries: 5
};
// 把rws 定义在外面是方便断开连接
let rws = null;

async function initWebsocket(obj) {
   let url = "ws://127.0.0.1:9999";
   // 建立websocket 连接
   rws = new ReconnectingWebSocket(url, [], options);

   rws.addEventListener("open", async () => {
      console.log('open')
    });

   rws.addEventListener("message", e => {
       if (e.data != 'next') {
       // 这里是你拿到数据后进行的处理
       //你可以调用action 来触发消息给页面上展示 例如 这些消息方法需要你自己来封装
        console.log(e.data)
        App.createCommand(e.data)
      }else if(e.data == 'next'){
        App.nextCommand()
      }
    });

   // 错误时进行的处理
   rws.addEventListener("error", e => {
       console.error(e.message);
    });
   // 关闭时进行的操作
   rws.addEventListener("close", () => {
       rws = null;
       console.info("asset service disconnected.");
    });
}

function Connect(name) {
 initWebsocket(name);
}

function Disconnect(result) {
   // 为什么要使用while呢,因为我们的页面上有可能不只 创建了一个websocket连接
   while(rws) {
        rws.close(1000, result);
    }
}

class App extends Component {

  constructor(props) {
      super(props);  
      this.state={
        active:0,
        arrays:[],
        waiting:[]
      }
      App.createCommand = this.createCommand.bind(this);
      App.nextCommand = this.nextCommand.bind(this);
  }

  componentDidMount() {
  	var that = this
    this.timer = setInterval(
      () => {
      	that.nextCommand()
      },
      1000
    );
    Connect('shihuan')
  }

  componentWillUnmount() {
    this.timer && clearInterval(this.timer);
  }

  nextCommand(){
  	var idx = this.state.active
  	idx = idx < this.state.arrays.length - 1 ? ++idx : idx
  	this.setState({
  		active:idx
  	})
    if(idx >= this.state.arrays.length - 10 && this.state.waiting.length > 0){
      this.setState({
        arrays:[...this.state.arrays,this.state.waiting[0]],
      })
    }
    this.scrollToBottom()
  }

  createCommand(command){
    var idx = this.state.active
    var data = {}
    data.name='Shihuan'
    data.cmd = command
    console.log(data)
    console.log(this)
    if(idx >= this.state.arrays.length - 10){
      this.setState({
        arrays:[...this.state.arrays,data],
      })
    }else{
      this.setState({
        waiting:[...this.state.waiting,data]
      })
    }
  }

  scrollToBottom() {
    if (this.messagesEnd) {
        ReactDOM.findDOMNode(this.messagesEnd).scrollIntoView({ behavior: "smooth" });
    }
  }

  render () {
    return (
      <View className="container">
      	<View className="inner">
	      	{this.state.arrays.map((cmd, i) => 
      		<View className={ i == this.state.active ? "cmd-item-active" :"cmd-item"}>
      			<View className="cmd-item-left">
      				<Text className="text">{cmd.name}</Text>
      			</View>
      			<Text className="text">{cmd.cmd}</Text>
      		</View>
	      	)}
          <View ref={(el) => { this.messagesEnd = el; }}></View>
      	</View>
      </View>
    )
  }
}

export default App
