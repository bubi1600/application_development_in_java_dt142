/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

var nrofpeople = 1;
var date = -1;
var time = -1;
var fname = -1;
var lname = -1;
var phonenr = -1;
var email = -1;

function openReservation()
{
    document.getElementById("reservationForm").style.display = "block";
    document.getElementById("reservation_overlay").style.display = "block";
}

function closeReservation()
{
    document.getElementById("reservationForm").style.display = "none";
    document.getElementById("reservation_overlay").style.display = "none";
    document.getElementById("reservation_step_one").style.display = "";
    document.getElementById("reservation_step_two").style.display = "none";
    document.getElementById("reservation_step_three").style.display = "none";
    document.getElementById("time_button_list").style.display = "none";
    date = -1;
}

function setNumOfGuests()
{
    nrofpeople = document.getElementById("num_of_guest").value;
    showAvailableTime();
}

function setDate()
{
    date = document.getElementById("reservation_date").value;
    showAvailableTime();
}

//When date, number of guests and type of food is choosen this function will
//display available time for choosen date.
function showAvailableTime()
{
    if(date != -1)
        document.getElementById("time_button_list").style.display = "block";
}


function setTime(id)
{
    time = String(document.getElementById(id).innerText).substring(0, 5);
    document.getElementById("reservation_step_one").style.display = "none";
    document.getElementById("reservation_step_two").style.display = "block";
    printSummery();
}

function changeReservation()
{
    closeReservation();
    openReservation();
}

function printSummery()
{
    document.getElementById("reservation_ndate").innerHTML = date;
    document.getElementById("reservation_num_guests").innerHTML = nrofpeople;
    document.getElementById("reservation_time").innerHTML = time;
}

function setReservationInput()
{
    name = document.getElementById("fname").value;
    surname = document.getElementById("lname").value;
    phone = document.getElementById("phonenr").value;
    email = document.getElementById("email").value; 
    printSummeryStepThree();
    sendToDatabase();
}

function printSummeryStepThree()
{
    document.getElementById("reservation_step_two").style.display = "none";
    document.getElementById("reservation_step_three").style.display = "block";
    document.getElementById("confirm_guest").innerHTML = nrofpeople;
    document.getElementById("confirm_date").innerHTML = date;
    document.getElementById("confirm_time").innerHTML = time;
    document.getElementById("confirm_name").innerHTML = fname;
    document.getElementById("confirm_surname").innerHTML = lname;
    document.getElementById("confirm_phone").innerHTML = phonenr;
    document.getElementById("confirm_email").innerHTML = email;
}

function sendToDatabase()
{
    
}