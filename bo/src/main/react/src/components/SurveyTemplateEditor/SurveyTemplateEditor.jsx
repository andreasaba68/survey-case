import React, { Component } from 'react';
import QuestionCard from './QuestionCard';
import * as common from '../common.jsx';
import './SurveyTemplateEditor.scss';
import uuid from 'uuid-random';

export default class SurveyTemplateEditor extends Component {

  state = {
    error: null,
    id: null,
    questions: []
  }

  componentDidMount() {
    console.log(this.props.match.params.id);
    const url = `/bo/svc/template/${this.props.match.params.id}`; // `/dummyData/survey.${this.props.surveyId}.json`; // "http://errorpioppolo/test"; // 
    common.fetchJson(url).then(d => this.setState(this.enrich(d)))
    .catch(e => this.setState({ error: `Error while loading the Survey Template : ${e.message}` }));
  }

  enrich(data) {
    data.questions.forEach((e,i) => { e.id= i+''; e.mode= 'view'; });
    return data;
  }

  saveSurveyTemplate() {
    const meth= this.state.id===null ? "POST" :"PUT";
    common.fetchJson2("/bo/svc/template",this.state,meth)
      .then(d => this.setState({id: d.id}) )
      .catch(e => this.setState({ error: `Error while saving the survey template : ${e.message}` }));
    ;
  }

  render() {

    if (!this.state.id)
      return common.progress();

      const h= this.handle;
      return (
      <div>
        {common.isError(this.state.error)}
        <div className="survey-template-editor">
          <input className="form-control" type="text" placeholder="Insert your title here" value={this.state.title} onChange={e=>h(e,"title")}/>
          {this.state.questions.map((q, i) => <QuestionCard key={"question_0_" + i} question={q} eventHandler={h} />)}
          <span>
            <button  onClick={(e) => h(null,"qst-append")}><i className="fas fa-plus-circle fa-4x "/></button>
            <button onClick={(e) => this.saveSurveyTemplate()} className="warn"><i className="fas fa-save fa-4x "/></button>
          </span>
        </div>
      </div>
    );
  }

  handle = (event, prop, id, i) => {
    const qs = [...this.state.questions];
    let p = qs.findIndex(o => o.id === id);
    let q = qs[p];
    switch (prop) {
      case "title": this.setState({ title: event.target.value });
        break;
      case "mode": q.mode = q.mode === "edit" ? "view" : "edit"; break;
      case "values": q.values[i] = event.target.value; break;
      case "type": q[prop] = i; break;
      case "ans-delete": q.values.splice(i,1); break;
      case "ans-before": q.values.splice(i,0,""); break;
      case "ans-after": q.values.splice(i+1,0,""); break;
      case "qst-delete": console.log(p); qs.splice(p,1); break;
      case "qst-append": qs.splice(99999,0,new Question()); break;
      case "qst-before": qs.splice(p,0,new Question()); break;
        default:
        q[prop] = event.target.value;
    }
    this.setState({ questions: qs });
  }

}

class Question {
  id= uuid();
  mode= "edit";
  category= "";
  text= "";
  type= "yn";
  values= ["","",""];
}
