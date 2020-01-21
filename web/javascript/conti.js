/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    let events = new EventSource("http://localhost:8080/MENU_MAXI_SERVER/rest/staff/conti");
    events.onmessage = (mess) => {
        let conto = JSON.parse(mess.data);

        let nuovoConto = `
                        <tr>
                            <th scope="row">${conto.tavolo}</th>
                            <td>${conto.totale}</td>
                            <td>${conto.metodo}</td>
                            <td>
                                <form action="rimuoviconto" method="GET">
                                    <input type="hidden" name="tavolo" value="${conto.tavolo}">
                                    <input type="submit" class="btn btn-primary" value="Stampa Conto">
                                </form>
                            </td>
                        </tr>`;

        $("#contenitore-conti").prepend(nuovoConto);

    }

});