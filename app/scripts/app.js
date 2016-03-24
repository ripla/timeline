(function (document) {
    'use strict';

    var app = document.querySelector('#app');

    // Sets app default base URL
    app.baseUrl = '/';
    if (window.location.port === '') {  // if production
        // Uncomment app.baseURL below and
        app.baseUrl = '/timeline/';
    }

    moment.locale('fi');

    app.addEventListener('dom-change', function () {
    });

    // See https://github.com/Polymer/polymer/issues/1381
    window.addEventListener('WebComponentsReady', function () {
        var timeline = document.querySelector('project-timeline');
        var form = document.querySelector('note-form');
        var backend = document.querySelector('backend-service');
        var addButton = document.querySelector('#add');

        addButton.addEventListener('click', function () {
            addButton.style.display = 'none';
            form.addNote();
        });

        form.addEventListener('note-form-submit', function (event) {
            var formData = event.detail;
            if (!formData.id || "" == formData.id) {
                backend.addNote(formData.project, formData.note, moment(formData.noteDate).valueOf());

            } else {
                backend.updateNote(formData.id, formData.project, formData.note, moment(formData.noteDate).valueOf());
            }

            if (!form.addAnotherNote) {
                addButton.style.display = "initial";
            }
        });

        form.addEventListener('note-form-cancel', function () {
            addButton.style.display = "initial";
        });

        timeline.addEventListener('project-timeline-point-click', function (event) {
            var pointObject = event.detail;
            form.editNote(pointObject.id, pointObject.project, pointObject.date, pointObject.note);
            addButton.style.display = 'none';
        });
    });

})(document);