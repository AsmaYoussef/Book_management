import React from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Home from "./pages/Home";
import BookList from "./components/BookList";
import BookForm from "./components/BookForm";
import AuthorList from "./components/AuthorList";
import AuthorForm from "./components/AuthorForm";
import PublisherList from "./components/PublisherList";
import PublisherForm from "./components/PublisherForm";
import TagList from "./components/TagList";
import TagForm from "./components/TagForm";

function App() {
  return (
      <Router>
        <div className="p-4">
          <nav className="mb-4 space-x-4">
            <Link to="/">Home</Link>
            <Link to="/books">Books</Link>
            <Link to="/authors">Authors</Link>
            <Link to="/publishers">Publishers</Link>
            <Link to="/tags">Tags</Link>
          </nav>

          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/books" element={<BookList />} />
            <Route path="/books/create" element={<BookForm />} />
            <Route path="/authors" element={<AuthorList />} />
            <Route path="/authors/create" element={<AuthorForm />} />
            <Route path="/publishers" element={<PublisherList />} />
            <Route path="/publishers/create" element={<PublisherForm />} />
            <Route path="/tags" element={<TagList />} />
            <Route path="/tags/create" element={<TagForm />} />
          </Routes>
        </div>
      </Router>
  );
}

export default App;
