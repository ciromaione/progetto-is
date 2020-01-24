/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
                
                let events = new EventSource("http://localhost:8080/MENU_MAXI_SERVER/rest/staff/ordini");
                events.onmessage = (mess) => {
                    let ordine = JSON.parse(mess.data);
                    
                    let num = parseInt($("#numero-ordini").text(), 10) + 1;
                    $("#numero-ordini").text(num);

                    let nuovoOrdine = `
                        <div class="col">
                        
                            <h2>Ordine Tavolo ${ordine.tavolo}</h2>
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th scope="col">Nome</th>
                                        <th scope="col">Quantitá</th>
                                        <th scope="col">Aggiunta</th>
                                        <th scope="col">Rimosso</th>
                                    </tr>
                                </thead>
                                <tbody>`;
                    let piatti = ordine.piatti;
                    for(let i = 0; i<piatti.length; ++i) {
                        let agg = "";
                        let rim = "";
                        piatto = piatti[i];
                        for(let j = 0; j<piatto.aggiunte.length; ++j) agg += piatto.aggiunte[j]+", ";
                        for(let j = 0; j<piatto.rimozioni.length; ++j) rim += piatto.rimozioni[j]+", ";
                        agg = agg.substring(0, agg.length-2);
                        rim = rim.substring(0, rim.length-2);
                        nuovoOrdine += `
                            <tr>
                                <th scope="row">${piatto.nomePiatto}</th>
                                <td>${piatto.quantita}</td>
                                <td>${agg}</td>
                                <td>${rim}</td>
                            </tr>`;
                    }
                                    
                    nuovoOrdine += `
                        </tbody>
                        </table>	
                        <form action="rimuoviordine" method="GET">
                            <input type="hidden" name="id" value="${ordine.id}">
                            <input type="submit" class="btn btn-success" value="Completato">
                        </form>
                        </div>`;
                    
                    $("#contenitore-ordini").prepend(nuovoOrdine);
                };
            });


