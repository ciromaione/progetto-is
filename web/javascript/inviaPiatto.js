/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//$(document).ready(() => {
//    $('#paginaordine').hide();

    class Piatto {
        constructor(id, nome, quantita, aggiunte, rimozioni) {
            this.idPiatto = id;
            this.nomePiatto = nome;
            this.quantita = quantita;
            this.aggiunte = aggiunte;
            this.rimozioni = rimozioni;
        }
    }

    class Ordine {
        constructor(tavolo) {
            this.id = 0;
            this.tavolo = tavolo;
            this.piatti = [];
            this.totaleCent = 0;
        }

        addPiatto = (piatto, totale) => {
            this.piatti.push(piatto);
            this.totaleCent += totale * piatto.quantita;
        }
    }


    let ordine = new Ordine(1);

    function aggiungiPiattoAOrdine(id, nome, prezzo) {
        let selQ = `#select-${id}`;
        let quantita = parseInt($(selQ).val());

        let aggiunte = [];
        let rimozioni = [];
        let totale = parseInt(prezzo);

        
        $.each($("input[name='check-add-"+id+"']:checked"), function () {
            let ingId= $(this).val();
            let nomeIng = $("#nomeing-"+ingId).val();
            let sovrapprezzo = parseInt($("#sovrapprezzo-"+ingId).val());
            aggiunte.push(nomeIng);
            totale += sovrapprezzo;
        });

        $.each($("input[name='check-rem-"+id+"']:checked"), function () {
            let ingId = $(this).val();
            let nomeIng = $("#nomeing-"+ingId).val();
            rimozioni.push(nomeIng);
        });

        let piatto = new Piatto(id, nome, quantita, aggiunte, rimozioni);

        ordine.addPiatto(piatto, totale);



        $('#totaleordine').text((parseFloat(ordine.totaleCent)/100).toFixed(2));

        let riga = `<tr><td>${nome}</td><td>${quantita}</td><td>${(parseFloat(totale)/100).toFixed(2)} €</td></tr>`;
        $('#tbody').append(riga);

        $("#modifica-piatto-"+id).modal("toggle");
    }
/*
    $('#riepilogo').click(() => {
        $('#paginapiatti').hide();
        $('#paginaordine').show();

        $('#totaleordine').append(parseFloat(ordine.totaleCent)/100);

        ordine.piatti.forEach((item) => {
            let riga = `<tr><td>${item.nome}</td><td>${item.quantita}</td></tr>`;
            $('#tbody').append(riga);
        });
    });*/
//});

$(document).ready(() => {

    $('#confermaordinebtn').click(() => {

        $.post("aggiungiordine", {"ordine":JSON.stringify(ordine)}, (data, status) => {});
    });


});