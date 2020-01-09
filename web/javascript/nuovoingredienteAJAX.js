$(document).ready(() => {
    
    class Ingrediente {
        constructor(nome, categoria, sovrapprezzo) {
            this.id = null;
            this.nome = nome;
            this.categoria = categoria;
            this.sovrapprezzoCent = sovrapprezzo;
        }
    }
    
    $('#salva-ing').click(() => {
        let nome = $('#nome-ing').val();
        let categoria = $('#cat-ing').val();
        let sovrapprezzo = $('#sovrapprezzo').val();
        
        let exp = /^[0-9]+(,|\.)[0-9]{2}$/;
        
        if(!exp.test(sovrapprezzo)) {
            return;
        }
        
        sovrapprezzo = sovrapprezzo.replace(/[,.]/, '');
        sovrapprezzo = parseInt(sovrapprezzo);
        
        let ingrediente = new Ingrediente(nome, categoria, sovrapprezzo);
        
        $.post("addingrediente", {"ingrediente": JSON.stringify(ingrediente)}, (data, status) => {
            $('#ingredienti').prepend(`<option value="${data}">${nome}</option>`);
            $('#ingredienti').val(data);
            $('#aggiungi-ingrediente').modal('toggle');
        });
    });
    
});

