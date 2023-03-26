import { Router, Switch, Route, Redirect } from 'react-router-dom';
import history from 'util/history';
import Home from 'pages/Home';
import Navbar from 'components/Navbar';
import Movies from 'pages/Private/Movies';
import Reviews from 'pages/Private/Reviews';
import PrivateRoute from 'components/PrivateRoute';

const Routes = () => (
  <Router history={history}>
    <Navbar />
    <Switch>
      <Redirect from="/" to="/home" exact />
      <Route path="/home">
        <Home />
      </Route>
      <PrivateRoute path="/movies">
        <Route path="/movies" exact>
          <Movies />
        </Route>
        <Route path="/movies/:movieId">
          <Reviews />
        </Route>
      </PrivateRoute>
    </Switch>
  </Router>
);

export default Routes;
