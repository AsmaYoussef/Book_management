import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

export default function BookList() {
    const [books, setBooks] = useState([]);

    const fetchBooks = async () => {
        try {
            const res = await axios.get("http://localhost:8080/api/books");
            setBooks(res.data);
        } catch (err) {
            console.error(err);
        }
    };

    const deleteBook = async (isbn) => {
        if (!window.confirm("Are you sure you want to delete this book?")) return;
        try {
            await axios.delete(`http://localhost:8080/api/books/${isbn}`);
            fetchBooks();
        } catch (err) {
            console.error(err);
        }
    };

    useEffect(() => {
        fetchBooks();
    }, []);

    return (
        <div className="p-4">
            <div className="flex justify-between items-center mb-4">
                <h2 className="text-2xl font-bold">Books</h2>
                <Link
                    to="/books/create"
                    className="bg-blue-500 text-white px-3 py-1 rounded"
                >
                    Add Book
                </Link>
            </div>

            <table className="table-auto border-collapse border border-gray-300 w-full">
                <thead>
                <tr>
                    <th className="border p-2">Title</th>
                    <th className="border p-2">Category</th>
                    <th className="border p-2">Price</th>
                    <th className="border p-2">Quantity</th>
                    <th className="border p-2">Author</th>
                    <th className="border p-2">Publisher</th>
                    <th className="border p-2">Actions</th>
                </tr>
                </thead>
                <tbody>
                {books.map((b) => (
                    <tr key={b.id}>
                        <td className="border p-2">{b.title}</td>
                        <td className="border p-2">{b.category}</td>
                        <td className="border p-2">{b.price}</td>
                        <td className="border p-2">{b.quantity}</td>
                        <td className="border p-2">{b.author?.name}</td>
                        <td className="border p-2">{b.publisher?.name}</td>
                        <td className="border p-2">
                            <button
                                className="bg-red-500 text-white px-2 py-1 rounded mr-2"
                                onClick={() => deleteBook(b.isbn)}
                            >
                                Delete
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}
