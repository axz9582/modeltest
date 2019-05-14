<html>
<head>

<script type="text/javascript">

var websocket= new WebSocket("ws://localhost:8080/modeltest/modeltestEndpoint");
websocket.onmessage= function processMessage(message){
	var jsonData = JSON.parse(message.data);
	var messagesTextArea = document.getElementById("messagesTextArea");
	if(jsonData.message !=null){ 
		messagesTextArea.value  += jsonData.message + "\n"; 
	}
}

function sendMessage(){
	
	var messageText = document.getElementById("messageText");

	websocket.send(messageText.value);
	messageText.value= "";
}

</script>
</head>
<body>
<textarea id="messagesTextArea" rows="10" cols="45"></textarea>

<br/>

<input type="text" id="messageText" size="50" />
<input type="button" value="send"  onclick="sendMessage();"/>
 </body>
</html>