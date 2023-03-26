import { ReactComponent as AuthImage } from 'assets/images/auth-image.svg';

import './styles.css';
import Login from './Login';

const Home = () => {
  return (
    <div className="container">
      <h1>Avalie Filmes</h1>
      <p>
        Diga o que você achou do seu <br />
        filme favorito
      </p>
      <AuthImage />
      <h1>Login</h1>
    </div>
  );
};

export default Home;
