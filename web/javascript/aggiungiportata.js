$(document).ready(() => {
    
    //classe per aggiunta ingrediente
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
            if(data == "false") $('#fallimento').modal("show");
            else {
                $('#ingredienti').prepend(`<option value="${data}">${nome}</option>`);
                $('#ingredienti').val(data);
            }
            $('#aggiungi-ingrediente').modal('toggle');
        });
    });
    
    //liste per ingredienti del piatto
    let ingFissi = [];
    let ingAgg = [];
    let ingRim = [];
    
    $('#aggiungi-btn').click(() => {
        
        let ingId = parseInt($('#ingredienti').val());
        if(ingFissi.includes(ingId) || ingAgg.includes(ingId) || ingRim.includes(ingId))
            return;
        
        let ingName = $("#ingredienti option[value='"+ingId+"']").text();
        let txt;
        
        let tipo = $('input[name=tipo-ing]:checked', '#form').val();
        switch(tipo) {
            case '0':
                txt = $('#txtfissi');
                ingFissi.push(ingId);
                break;
            case '1':
                txt = $('#txtagg');
                ingAgg.push(ingId);
                break;
            case '2':
                txt = $('#txtrim');
                ingRim.push(ingId);
                break;
        }
        
        if(txt.val() === '')
            txt.append(ingName);
        else
            txt.append(", "+ingName);
        
    });
    
    $('#form').submit(() => {
        $('#ing-fissi').val(JSON.stringify(ingFissi));
        $('#ing-agg').val(JSON.stringify(ingAgg));
        $('#ing-rim').val(JSON.stringify(ingRim));
        return true;
    });
    
    $('#form-ing').submit(() => {
        return false;
    });
    
});

