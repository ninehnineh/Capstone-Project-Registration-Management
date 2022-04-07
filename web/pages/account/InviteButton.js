//// button-1
//document.getElementById("1").addEventListener('click', () => {
//    Confirm.open({
//        title: 'Confirm',
//        message: 'Are you sure?',
//        onok: () => {
//            document.getElementById("1").innerHTML = "Invited";
//        }
//    })
//});
//
//// button-2
//document.getElementById("2").addEventListener('click', () => {
//    Confirm.open({
//        title: 'Confirm',
//        message: 'Are you sure?',
//        onok: () => {
//            document.getElementById("2").innerHTML = "Invited";
//        }
//    })
//});
//// button-3
//document.getElementById("3").addEventListener('click', () => {
//    Confirm.open({
//        title: 'Confirm',
//        message: 'Are you sure?',
//        onok: () => {
//            document.getElementById("3").innerHTML = "Invited";
//        }
//    })
//});
//// button-4
//document.getElementById("4").addEventListener('click', () => {
//    Confirm.open({
//        title: 'Confirm',
//        message: 'Are you sure?',
//        onok: () => {
//            document.getElementById("4").innerHTML = "Invited";
//        }
//    })
//});
//// button-5
//document.getElementById("5").addEventListener('click', () => {
//    Confirm.open({
//        title: 'Confirm',
//        message: 'Are you sure?',
//        onok: () => {
//            document.getElementById("5").innerHTML = "Invited";
//        }
//    })
//});
//// button-6
//document.getElementById("6").addEventListener('click', () => {
//    Confirm.open({
//        title: 'Confirm',
//        message: 'Are you sure?',
//        onok: () => {
//            document.getElementById("6").innerHTML = "Invited";
//        }
//    })
//});
//
//// invite function
//const Confirm = {
//    open(options) {
//        options = Object.assign({}, {
//            title: '',
//            message: '',
//            okText: 'OK',
//            cancelText: 'Cancel',
//            onok: function() {},
//            oncancel: function() {}
//        }, options);
//
//        const html = `
//            <div class="confirm">
//                <div class="confirm__window">
//                    <div class="confirm__titlebar">
//                        <span class="confirm__title">${options.title}</span>
//                        <button class="confirm__close">&times;</button>
//                    </div>
//                    <div class="confirm__content">${options.message}</div>
//                    <div class="confirm__buttons">
//                        <button class="confirm__button confirm__button--ok confirm__button--fill">${options.okText}</button>
//                        <button class="confirm__button confirm__button--cancel">${options.cancelText}</button>
//                    </div>
//                </div>
//            </div>
//        `;
//
//        const template = document.createElement('template');
//        template.innerHTML = html;
//
//        // Elements
//        const confirmEl = template.content.querySelector('.confirm');
//        const btnClose = template.content.querySelector('.confirm__close');
//        const btnOk = template.content.querySelector('.confirm__button--ok');
//        const btnCancel = template.content.querySelector('.confirm__button--cancel');
//
//        confirmEl.addEventListener('click', e => {
//            if (e.target === confirmEl) {
//                options.oncancel();
//                this._close(confirmEl);
//            }
//        });
//
//        btnOk.addEventListener('click', () => {
//            options.onok();
//            this._close(confirmEl);
//        });
//
//        [btnCancel, btnClose].forEach(el => {
//            el.addEventListener('click', () => {
//                options.oncancel();
//                this._close(confirmEl);
//            });
//        });
//
//        document.body.appendChild(template.content);
//    },
//
//    _close(confirmEl) {
//        confirmEl.classList.add('confirm--close');
//
//        confirmEl.addEventListener('animationend', () => {
//            document.body.removeChild(confirmEl);
//        });
//    }
//};