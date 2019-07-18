class WS{
    constructor(url){
        this.url = url
        this.ws = new WebSocket(`${url}`);
        this.ws.onopen = function (evt) {
            console.log('Connection open ...');
        };

        this.ws.onclose = function (evt) {
            console.log('Connection closed.');
        };
        this.ws.onmessage = (evt)=> {
            console.log(evt.data)
            let data = JSON.parse(evt.data)
            console.log(data)
            this.text(data)
        };

    }
    deletePeople(data){

    }
    addPeople(data){

    }
    text(data){
        roomMessage.message.push(data)
    }
    image(data){

    }
    send(message) {
        ws.send(message)
    }

}
