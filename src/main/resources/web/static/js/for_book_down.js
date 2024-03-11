

function change_button_style() {

    let button = document.getElementById('books-block-book-body-down');

    // this.style.borderStyle = "outset";
    // button.style.borderStyle
    button.style.height = "10px";

    button.style.backgroundColor = 'red';
}




let stars =
    document.getElementsByClassName("star");
let output =
    document.getElementById("output");



// Funtion to update rating
function gfg(n) {
    console.log(n);
    remove();
    for (let i = 0; i < n; i++) {
        if (n == 1) cls = "one";
        else if (n == 2) cls = "two";
        else if (n == 3) cls = "three";
        else if (n == 4) cls = "four";
        else if (n == 5) cls = "five";
        stars[i].className = "star " + cls;
    }
    output.innerText = "Your rating: " + n + "/5";
}

// To remove the pre-applied styling
function remove() {
    let i = 0;
    while (i < 5) {
        stars[i].className = "star";
        i++;
        console.log(i+" - chi black starlandi")

    }
}


var temp_text_area;


function clicked_edit_btn(id){

    let textarea = document.getElementById("textarea-id-"+id);
    textarea.removeAttribute("readonly");
    textarea.style.backgroundColor = "beige";

    temp_text_area = textarea.value;

    document.getElementById("edit-btn-id-"+id).style.display = "none";
    document.getElementById("buttons-div-id-"+id).style.display = "flex";



}

function clicked_cancel_btn(id){

    document.getElementById("edit-btn-id-"+id).style.display = "flex";
    document.getElementById("buttons-div-id-"+id).style.display = "none";

    let textarea = document.getElementById("textarea-id-"+id);
    textarea.style.backgroundColor = "lightpink";
    textarea.setAttribute("readonly","true");

    textarea.value = temp_text_area;

}


function add_comment_btn(id){

    let add_block = document.getElementById(id+"-comment-block");
    add_block.style.display = "flow-root";

}

function cancel_add_comment(id){

    let add_block = document.getElementById(id+"-comment-block");
    add_block.style.display = "none";

}
