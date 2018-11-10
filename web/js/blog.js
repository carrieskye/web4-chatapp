let webSocket;

function openSocket() {
    webSocket = new WebSocket('ws://localhost:8080/blog');

    webSocket.onopen = function () {
        webSocket.send(JSON.stringify({'action': 'getPreviousComments', 'data': undefined}));
    };

    webSocket.onmessage = function (event) {
        const parsed = JSON.parse(event.data);

        if (parsed.action === 'previousComments') {
            for (let i = 0; i < parsed.data.length; i++) {
                writeBlog(parsed.data[i]);
            }
        } else if (parsed.action === 'newComment') {
            writeComment(parsed.data.blogId, parsed.data);
        }
    };
}

async function send(blogId) {
    const data = {
        blogId: blogId,
        name: document.getElementById(`name${blogId}`).value,
        text: document.getElementById(`comment${blogId}`).value,
        rating: document.getElementById(`rating${blogId}`).value
    };
    if (data.rating >= 0 && data.rating <= 10) {
        webSocket.send(JSON.stringify({action: 'newComment', data: JSON.stringify(data)}));
        cleanForm(blogId);
    }
}

function writeBlog(blog) {
    const blogDiv = createElement(document.getElementById('blogs'), 'div', `blog${blog.blogId}`, 'card');
    createElement(blogDiv, 'div', undefined, 'blogText', blog.text);
    createElement(blogDiv, 'div', `comments${blog.blogId}`);
    for (let j = 0; j < blog.comments.length; j++) {
        writeComment(blog.blogId, blog.comments[j]);
    }
    writeNewCommentDiv(blog.blogId);
}

function writeComment(blogId, comment) {
    const newCommentDiv = createElement(document.getElementById(`comments${blogId}`), 'div', undefined, 'comment');

    const commentHeader = createElement(newCommentDiv, 'div');
    createElement(commentHeader, 'i', undefined, 'fa fa-angle-double-right');
    createElement(commentHeader, 'span', undefined, 'commentName', comment.name);
    for (let i = 0; i < 10; i++) {
        if (i < comment.rating) {
            createElement(commentHeader, 'i', undefined, 'fa fa-star');
        } else {
            createElement(commentHeader, 'i', undefined, 'fa fa-star lessVisible');
        }
    }

    createElement(newCommentDiv, 'div', undefined, 'commentText', comment.text);
}

function writeNewCommentDiv(blogId) {
    const newCommentDiv = createElement(document.getElementById(`blog${blogId}`), 'div', `newComment${blogId}`, 'newComment');

    const toggleReply = createElement(newCommentDiv, 'a', undefined, undefined, 'Reply');
    createElement(toggleReply, 'i', `toggleReplyIcon${blogId}`, 'fa fa-chevron-down toggleReply');
    toggleReply.onclick = () => {
        let form = document.getElementById(`form${blogId}`);
        let toggleReplyIcon = document.getElementById(`toggleReplyIcon${blogId}`);
        if (!form.hidden) {
            toggleReplyIcon.className = 'fa fa-chevron-down toggleReply';
        } else {
            toggleReplyIcon.className = 'fa fa-chevron-up toggleReply';
        }
        form.hidden = !form.hidden;
    };

    writeNewCommentForm(blogId);
}

function writeNewCommentForm(blogId) {
    const form = createElement(document.getElementById(`newComment${blogId}`), 'div', `form${blogId}`, 'form');
    form.hidden = true;

    createFormGroup('input', 'Name: ', 'name', 'text', blogId);
    const ratingFormGroup = createFormGroup('input', 'Rating: ', 'rating', 'number', blogId);
    document.getElementById(`rating${blogId}`).className = 'commentRatingInput';
    document.getElementById(`rating${blogId}`).value = 0;
    const ratingDiv = createElement(ratingFormGroup, 'div');
    for (let i = 0; i < 10; i++) {
        const star = createElement(ratingDiv, 'i', `ratingStar${blogId}-${i}`, 'fa fa-star newCommentStarLessVisible');
        star.onclick = ((event) => {
            let starNumber = event.target.id.split('-')[1];
            document.getElementById(`rating${blogId}`).value = (parseInt(starNumber) + 1);
            for (let j = 0; j < 10; j++) {
                if (j <= starNumber) {
                    document.getElementById(`ratingStar${blogId}-${j}`).className = 'fa fa-star newCommentStar';
                } else {
                    document.getElementById(`ratingStar${blogId}-${j}`).className = 'fa fa-star newCommentStarLessVisible';
                }
            }
        });
    }

    createFormGroup('textarea', 'Comment: ', 'comment', undefined, blogId);
    document.getElementById(`comment${blogId}`).rows = 5;
    document.getElementById(`comment${blogId}`).className = 'newCommentText form-control';

    const addComment = createElement(form, 'button', undefined, 'form-control', 'Add comment');
    addComment.onclick = () => {
        send(blogId);
    };
}

function createFormGroup(elementType, label, id, type, blogId) {
    const formGroup = createElement(document.getElementById(`form${blogId}`), 'div', undefined, 'form-group');
    createElement(formGroup, 'label', undefined, undefined, label, undefined, id + blogId);
    createElement(formGroup, elementType, id + blogId, 'form-control', undefined, type);
    return formGroup;
}

function cleanForm(blogId) {
    document.getElementById(`form${blogId}`).hidden = true;
    document.getElementById(`toggleReplyIcon${blogId}`).className = 'fa fa-chevron-down toggleReply';
    document.getElementById(`name${blogId}`).value = '';
    document.getElementById(`comment${blogId}`).value = '';
    document.getElementById(`rating${blogId}`).value = '';
    for (let i = 0; i < 10; i++) {
        document.getElementById(`ratingStar${blogId}-${i}`).className = 'fa fa-star newCommentStarLessVisible';
    }
}

function createElement(parent, type, id, className, text, inputType, forElement) {
    const newElement = document.createElement(type);
    newElement.id = id;
    newElement.className = className;
    newElement.textContent = text;
    newElement.type = inputType;
    newElement.for = forElement;
    parent.appendChild(newElement);
    return newElement;
}