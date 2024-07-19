var editor = document.querySelector(".code-editor textarea"),
    visualizer = document.querySelector(".code-editor code");

editor.addEventListener("input", (e) => {
   visualizer.innerHTML = e.target.value;
   Prism.highlightAll();
})