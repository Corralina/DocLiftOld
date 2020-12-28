<#import "../parts/dom.ftl" as dom>
<@dom.dom>
    <div class="mt-5 shadow-lg mb-5 bg-white rounded d-flex flex-row" style="min-height: 70vh">
        <div class="d-flex align-items-center justify-content-center" style="width: 60%; position: relative">
            <div class="" style="position: absolute; top: 20px; left: 15px; color: red">
                <#if error??>
                    <p>* ${error?ifExists}</p>
                </#if>
            </div>
            <div class="my_login_text mt-5 p-4 d-flex flex-column justify-content-around my_res_doc_info" >
                <label>
                    <#if act.status?ifExists.finish?ifExists>
                        Завершено
                    <#elseIf act.status?ifExists.revers?ifExists>
                        Повернено
                    <#elseIf act.status?ifExists.draft?ifExists>
                        Пауза
                        <form method="get" action="/actPlay/${act?ifExists.id?ifExists}">
                            <input type="hidden" value="${_csrf.token}" name="_csrf">
                            <button class="btn btn-primary my_button mt-3" type="submit">Play</button>
                        </form>
                        <form method="get" action="/actEdit/${act?ifExists.id?ifExists}">
                            <input type="hidden" value="${_csrf.token}" name="_csrf">
                            <button class="btn btn-primary my_button mt-3" type="submit">Edit act</button>
                        </form>
                        <form method="get" action="/actFileEdit/${act?ifExists.id?ifExists}">
                            <input type="hidden" value="${_csrf.token}" name="_csrf">
                            <button class="btn btn-primary my_button mt-3" type="submit">Edit file</button>
                        </form>
                    <#else>
                        Обробка
                    <form method="get" action="/actPause/${act?ifExists.id?ifExists}">
                        <input type="hidden" value="${_csrf.token}" name="_csrf">
                        <button class="btn btn-primary my_button mt-3" type="submit">Pause</button>
                    </form>
                    </#if>
                </label>
                <label>Документ № ${act?ifExists.documentAct?ifExists.description?ifExists}</label>
                <label>Дата: ${act?ifExists.date?ifExists}</label>
                <label>коментар: ${act?ifExists.coment?ifExists}</label>
                <label>Документ завантажив: ${act?ifExists.author?ifExists.information?ifExists.individual?ifExists.initials?ifExists}</label>
            </div>

        </div>

        <div class="d-flex flex-row align-items-center justify-content-center p-4 my_res_doc_info_block">
            <div class="my_login_text mt-5 d-flex flex-column justify-content-around my_res_doc_info">
                <label>Збір підписіів</label>
                <#list timings as timing>
                    <div class="my_login_text mt-5 d-flex flex-column act_bloc_user">
                        <label>Посада: ${timing?ifExists.post?ifExists}</label>
                        <label>Хто: ${timing?ifExists.user?ifExists.information?ifExists.individual?ifExists.initials?ifExists}</label>
                        <#if timing.sing?ifExists>
                            <label>Підписано - ${timing.date?ifExists}</label>
                        <#else>
                            <label>Очікується підтвердження</label>
                        </#if>
                        <#if timing.sing?ifExists>

                        <#elseIf timing.user.username==username?ifExists>
                            <form method="get" action="/actConfirm/${act?ifExists.id?ifExists}">
                                <input type="hidden" value="${_csrf.token}" name="_csrf">
                                <input type="hidden" value="${timing.getId()}" name="timing">
                                <button class="btn btn-primary my_button mt-3" type="submit">Підписати</button>
                            </form>
                        </#if>
                    </div>
                </#list>
            </div>
        </div>

    </div>


    <script type="text/javascript" charset="utf8" src="/static/main.js"></script>
    <script type="text/javascript" charset="utf8" src="/static/pathCorrect.js"></script>


</@dom.dom>