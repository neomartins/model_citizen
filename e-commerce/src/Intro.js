/**
 * Created by hmartins@worth.systems on 31/05/2018.
 */
import React from 'react';

const Intro = (props) => {
  return (
    <p className="App-intro">
      {props.user.name} ,To get started, edit <code>src/App.js</code> and save to reload.
    </p>
  )
};

export default Intro