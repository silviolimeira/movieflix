import { Link, NavLink } from 'react-router-dom';
import { useState, useEffect } from 'react';
import { SpringPage } from 'types/vendor/spring';
import { requestBackend } from 'util/requests';
import { AxiosRequestConfig } from 'axios';

import './styles.css';
import { Movie } from 'types/movie';
import Loader from 'components/Loader';

const Movies = () => {
  const [page, setPage] = useState<SpringPage<Movie>>();
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    const params: AxiosRequestConfig = {
      method: 'GET',
      url: '/movies',
      withCredentials: true,
      params: {
        page: 0,
        size: 12,
      },
    };

    setIsLoading(true);
    requestBackend(params)
      .then((response) => {
        setPage(response.data);
      })
      .finally(() => {
        setIsLoading(false);
      });
  }, []);

  return (
    <div className="container movies-container">
      <div className="row movies-title-container">
        <h1>Tela listagem de filmes</h1>
      </div>
      <div className="row movies-data-container">
        {isLoading ? (
          <>
            <Loader />
          </>
        ) : (
          page?.content.map((movie) => (
            <div
              className="col-sm-6 col-lg-4 col-xl-3 movies-link"
              key={movie.id}
            >
              <Link to={`/movies/${movie.id}`}>Acessar /movies/{movie.id}</Link>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default Movies;
