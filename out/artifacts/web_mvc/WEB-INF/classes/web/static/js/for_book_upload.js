function change_name_file(filename,id){
    var idd = id;
    let text = document.getElementById(id);
    text.innerHTML = filename;
    console.log(filename,id);
}

function change_succes_button_color(bool){
    let button = document.getElementById("succes-body-goto-body");
   
    if (bool==1){
        button.style.backgroundColor = "rgb(24, 195, 195)";
    } else{
        button.style.backgroundColor = "aqua";
    }
   
}