<#import "../parts/dom.ftl" as dom>
<@dom.dom>
    <h1 class="justify-content text-success"> ${succes?ifExists} </h1>
    <h1 class="justify-content text-danger"> ${error?ifExists} </h1>
    <h1 class="justify-content-center text-center"> Резолюція № ${resolution?ifExists.document?ifExists.number?ifExists} </h1>


    <div class="mt-5 shadow-lg mb-5 bg-white rounded d-flex flex-row" style="min-height: 50vh">
        <div class="shadow-lg mb-5 bg-white rounded border p-2 d-flex flex-row  justify-content-around my_resolution">

            <div style="width: 100%">
                <p class="text-center">
                    ${resolution?ifExists.agrees?ifExists.information?ifExists.individual?ifExists.post?ifExists}
                </p>
                <p class="text-center">
                    Сомого апеляційного
                </p>
                <p class="text-center">
                    адміністративного суду
                </p>
                <div class="my_resolution_line1"></div>
                <div class="my_resolution_line2"></div>
                <#list performers?ifExists as performer>
                    <p class="text-center">
                        ${performer?ifExists.user?ifExists.information?ifExists.individual?ifExists.initials?ifExists}
                    </p>
                    <p class="text-center">
                        ${performer?ifExists.coment?ifExists}
                    </p>
                </#list>
                <div class="my_resolution_agree" id="cap">
                    <div class="d-flex flex-row  justify-content-center my_resolution_caption_block">

                    </div>
                    <p class="text-right">
                        ${resolution?ifExists.agrees?ifExists.information?ifExists.individual?ifExists.initials?ifExists}
                    </p>
                    <p class="text-right">
                        ${resolution?ifExists.date?ifExists}
                    </p>
                </div>
            </div>

        </div>

        <div class="d-flex flex-row p-4 pl-3">
            <div class="my_resolution_text d-flex flex-column justify-content-around " >
                <label>${status?ifExists}</label>
                <label>Дата резолюції: ${resolution?ifExists.data?ifExists}</label>
                <label>коментар: ${resolution?ifExists.coment?ifExists}</label>
                <label>Розписав резолюцію: ${resolution?ifExists.filling?ifExists.information?ifExists.individual?ifExists.surname?ifExists} ${resolution?ifExists.filling?ifExists.information?ifExists.individual?ifExists.name?ifExists} ${resolution?ifExists.filling?ifExists.information?ifExists.individual?ifExists.middlename?ifExists}</label>
                <div class="d-flex flex-row">
                    <a class="nav-link" id="add_doc" href="/pdf/${resolution?ifExists.document?ifExists.name?ifExists}" title="Відкрити документ" target="_blank"><img src="/static/openDocument.svg" alt=""></a>
<#--                    <a class="nav-link" id="add_doc" href="/img/${resolution?ifExists.document?ifExists.image?ifExists}" title="Відкрити картинку" target="_blank"><img src="/static/openImage.svg" alt=""></a>-->
                </div>
            </div>
        </div>
    </div>

    <#if work?ifExists>
        <div class="mt-5 shadow-lg mb-5 bg-white rounded d-flex flex-row justify-content-around p-4">
            <div>
                <form action="/resolutionVisa" method="post">
                    <button class="btn btn-primary my_button mt-2" type="submit">Підтвердити</button>
                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                    <input type="hidden" value="${resolution?ifExists.id?ifExists}" name="resolution">
                </form>
            </div>
            <div>
                <form action="/resolutionRevers" method="post">
                    <div class="input-group mt-3">
                        <input type="text" style="width: 310px" class="form-control" name="coment" placeholder="Причина повернення" aria-label="Recipient's username" aria-describedby="button-addon2">
                        <div class="input-group-append">
                            <button class="btn btn-primary my_button" type="submit" id="button-addon2">Повернути</button>
                        </div>
                        <input type="hidden" value="${_csrf.token}" name="_csrf">
                        <input type="hidden" value="${resolution?ifExists.id?ifExists}" name="resolution">
                    </div>
                </form>

            </div>
        </div>
    </#if>

</@dom.dom>