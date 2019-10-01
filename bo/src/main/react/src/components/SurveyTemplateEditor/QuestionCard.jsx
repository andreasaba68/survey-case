import React from 'react';
import './QuestionCard.scss';

const QuestionCard = (props) => {
    const h= props.eventHandler;

    const QButton = (props0) => {
        return (<button onClick={(e) => h(e, "type", props.question.id, props0.type)} ><i className={"fas "+props0.iconName} ></i></button>);
    }

    let res =
        <div key={"QuestionCard_view_" + props.question.id} className="qc-view">

            <div>
                <h4 style={{ display: "inline-block" }} >{props.question.category}</h4>

                <button onClick={(e) => h(e, "qst-before", props.question.id)}>
                    <i className="fas fa-plus-circle"></i>
                </button>

                <button onClick={(e) => h(e, "mode", props.question.id)}>
                    <i className="fas fa-edit"></i>
                </button>

            </div>
            <div>
                <p>{props.question.text}</p>
                {renderType(props)}
            </div>
        </div>
        ;

    if (props.question.mode === "edit") {
        res =
            <div key={"QuestionCard_edit_" + props.question.id} className="qc-edit">

                <div>
                    <input type="text" value={props.question.category} onChange={(e) => h(e, "category", props.question.id)} />
                    <button className="toggle-button"
                        onClick={(e) => h(e, "qst-delete", props.question.id)}>
                        <i className="fas fa-minus" ></i>
                    </button>
                    <button className="toggle-button"
                        onClick={(e) => h(e, "mode", props.question.id)}>
                        <i className="fas fa-eye" ></i>
                    </button><br />
                    <QButton type="yn" iconName="fa-question" />
                    <QButton type="freeText" iconName="fa-i-cursor" />
                    <QButton type="multipleChoice" iconName="fa-check-square" />
                    <QButton type="singleChoice" iconName="fa-check-circle" />
                </div>

                <div>
                    <input type="text" value={props.question.text} onChange={(e) => h(e, "text", props.question.id)} />
                    {renderType(props)}
                </div>
            </div>
            ;
    }

    return res;
};

const renderType = (props) => {
    const h= props.eventHandler;
    const q = props.question;
    const view = q.mode === "view";
    const m = view ? "_view_" : "_edit_";

    switch (q.type) {
        case "yn":
            return (
                <div className="qc-t-ynd">
                    <div>
                        <div><input type="radio" name={q.id} /></div>
                        <p>yes</p>
                    </div>
                    <div>
                        <div><input type="radio" name={q.id} /></div>
                        <p>no</p>
                    </div>
                    <div>
                        <div><input type="radio" name={q.id} defaultChecked /></div>
                        <p>I don't know</p>
                    </div>
                </div>
            );

        case "freeText":
            return (<div className="qc-t-freeText">
               <textarea id="message" rows="3"></textarea>
            </div>);

        case "multipleChoice":
            return q.values.length===0 ?
                <div key={`v_multipleChoice_${m}${q.id}_0`} className="qc-t-multipleChoiced">
                    <i style={{position: "absolute", left: "45%", top: "85%"}} onClick={(e)=> h(e, "ans-before", props.question.id, 0) } 
                        className="fas fa-plus-circle fa-2x" />
                </div>
                : q.values.map((t, i) => {
                    return (
                        <div key={`v_multipleChoice_${m}${q.id}_${i}`} className="qc-t-multipleChoiced">
                            <div><input type="checkbox"/></div>
                            {
                                view ? <p>{t}</p> :
                                    <React.Fragment>
                                        <input key={`v_multipleChoice_input_${m}${q.id}_${i}`}
                                            type="text" value={t} onChange={(e) => h(e, "values", props.question.id, i)} />
                                        <i onClick={(e)=> h(e, "ans-before", props.question.id, i) } 
                                            style={{position: "absolute", right: "-0.6em", top: "-20%"}} className="fas fa-plus-circle" />
                                        <i onClick={(e)=> h(e, "ans-delete", props.question.id, i) } 
                                            style={{position: "absolute", right: "-0.6em", bottom: "30%"}} className="fas fa-minus-circle" />

                                        { 
                                            (i!== (q.values.length-1)) ? null :
                                            <i style={{position: "absolute", right: "-0.6em", bottom: "-20%"}} onClick={(e)=> h(e, "ans-after", props.question.id, i) } 
                                                        className="fas fa-plus-circle" />
                                        }

                                    </React.Fragment>
                            }

                        </div>
                    )
                });

        case "singleChoice":
            return q.values.length===0 ?
                <div key={`v_multipleChoice_${m}${q.id}_0`} className="qc-t-singleChoiced">
                    <i style={{position: "absolute", left: "45%", top: "85%"}} onClick={(e)=> h(e, "ans-before", props.question.id, 0) } 
                        className="fas fa-plus-circle fa-2x" />
                </div>
                : q.values.map((t, i) => {
                return (
                    <div key={`v_singleChoice_${q.id}_${i}`} className="qc-t-singleChoiced">
                        <div><input type="radio" name={q.id} /></div>
                        {
                            view ?
                                <p>{t}</p> :
                                <React.Fragment>
                                    <input key={`v_singleChoice_input_${m}${q.id}_${i}`}
                                        type="text" value={t} onChange={(e) => h(e, "values", props.question.id, i)} />
                                        <i onClick={(e)=> h(e, "ans-before", props.question.id, i) } 
                                            style={{position: "absolute", right: "-0.6em", top: "-20%"}} className="fas fa-plus-circle" />
                                        <i onClick={(e)=> h(e, "ans-delete", props.question.id, i) } 
                                            style={{position: "absolute", right: "-0.6em", bottom: "30%"}} className="fas fa-minus-circle" />

                                        { 
                                            (i!== (q.values.length-1)) ? null :
                                            <i style={{position: "absolute", right: "-0.6em", bottom: "-20%"}} onClick={(e)=> h(e, "ans-after", props.question.id, i) } 
                                                        className="fas fa-plus-circle" />
                                        }
                                </React.Fragment>
                        }
                    </div>
                )
            }
        );

        default: break;
    }

    return (<div className="bg-danger">Type: {q.type}</div>);

}

export default QuestionCard;
