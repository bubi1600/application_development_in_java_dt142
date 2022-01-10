/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */



function AddFunction()
{
  var table = document.getElementById("t3");
  var row = table.insertRow(-1);
  var cell_1 = row.insertCell(0);
  var cell_2 = row.insertCell(1);
  var cell_3 = row.insertCell(2);
  var element1 = document.createElement('input');
  element1.type="text";
  cell_1.appendChild(element1);

  var element2 = document.createElement('input');
  element2.type="number";
  cell_2.appendChild(element2); 

  var element3 = document.createElement('input');
  element3.type="button";
  element3.value="Delete";
  element3.setAttribute("onclick", 'deleteRow(this)');
  cell_3.appendChild(element3); 
}


function RemoveFunction(){
    var tableID="t1";
    var table = document.getElementById(tableID);
    var rowCount = table.rows.length;
    if(rowCount>0){   
        //you had type in deletRow. Also, you can pass in -1 to remove the last row        
        table.deleteRow(-1); 
    }
}

function deleteRow(r) {
  var i = r.parentNode.parentNode.rowIndex;
  document.getElementById("t3").deleteRow(i);
}

function t4AddFunction() {
  var table = document.getElementById("t4");
  var row = table.insertRow(-1);
  var cell1 = row.insertCell(0);
  var cell2 = row.insertCell(1);
  var cell3 = row.insertCell(2);
  var element1 = document.createElement('input');
  element1.type="text";
  cell1.appendChild(element1);

  var element2 = document.createElement('input');
  element2.type="number";
  cell2.appendChild(element2); 

  var element3 = document.createElement('input');
  element3.type="button";
  element3.value="Delete";
  element3.setAttribute("onclick", 't4deleteRow(this)')

  cell3.appendChild(element3); 
}

function t4deleteRow(r) {
  var i = r.parentNode.parentNode.rowIndex;
  document.getElementById("t4").deleteRow(i);
}

function t1deleteRow(r) {
  var i = r.parentNode.parentNode.rowIndex;
  document.getElementById("t1").deleteRow(i);
}


