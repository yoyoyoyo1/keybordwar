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
            this[data.type](data)
        };

    }
    addPeople(data){
        for(let people of roomPeople){
            if(people.id == data.id){
                people = data
                return
            }
        }
        roomPeople.push(data)
        people[data.id] = data
        localStorage.setItem("people", JSON.stringify(people));
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
