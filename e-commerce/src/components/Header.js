/**
 * Created by hmartins@worth.systems on 01/06/2018.
 */
import React from 'react';

const Header = (props) => {
  return (
    <div className="navbar-fixed">
      <nav className="teal lighten-2">
        <div className="nav-wrapper">
          <div className="brand-logo center">Welcome {props.name} to React!</div>
        </div>
      </nav>

    </div>
  );
};

export default Header;