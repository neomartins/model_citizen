import React, {Component} from 'react';

import Header from './components/Header';
import Grid from './components/Grid';
import Form from './components/Form';

const styles = {
  textAlign: 'center',
  margin: 0,
  padding: 0,
  fontFamily: 'sans-serif',
};

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      clients: [],
      name: 'Henrique',
      currentName: '',
      currentAge: '',
      currentBirth: '',
    };

  }

  componentWillMount() {
    fetch('http://localhost:8080/client').then(response => response.json()).then(data => this.setState({clients: data}))
  }

  handleChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;

    this.setState({
      [name]: value
    });
  };

  handleSubmit = (event) => {
    fetch('http://localhost:8080/client', {
      method: 'POST',
      body: JSON.stringify({
        name: this.state.currentName,
        age: this.state.currentAge,
        birthDate: this.state.currentBirth
      }),
      headers: {
        'Content-Type': 'application/json'
      }
    }).then(response => response.json);
    event.preventDefault();
  };

  render() {
    return (
      <div className={styles}>
        <Header user={this.state}/>

        <Form
          currentName={this.state.currentName}
          currentAge={this.state.currentAge}
          currentBirth={this.state.currentBirth}
          handleChange={this.handleChange}
          handleSubmit={this.handleSubmit}
        />
        <Grid clients={this.state.clients}/>
      </div>
    );
  }
}

export default App;
