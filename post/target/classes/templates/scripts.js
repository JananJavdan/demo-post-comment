
document.addEventListener("DOMContentLoaded", function() {
    // Example of handling form submissions
    document.getElementById("new-post-form")?.addEventListener("submit", function(event) {
        event.preventDefault();
        const title = document.getElementById("title").value;
        const content = document.getElementById("content").value;
        console.log("New post submitted", { title, content });
        alert("New post submitted");
    });

    document.getElementById("update-post-form")?.addEventListener("submit", function(event) {
        event.preventDefault();
        const title = document.getElementById("title").value;
        const content = document.getElementById("content").value;
        console.log("Post updated", { title, content });
        alert("Post updated");
    });

    document.getElementById("new-comment-form")?.addEventListener("submit", function(event) {
        event.preventDefault();
        const postId = document.getElementById("post-id").value;
        const comment = document.getElementById("comment").value;
        console.log("New comment submitted", { postId, comment });
        alert("New comment submitted");
    });
});
