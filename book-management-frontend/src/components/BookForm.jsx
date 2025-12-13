import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function BookForm() {
    const [title, setTitle] = useState("");
    const [category, setCategory] = useState("");
    const [isbn, setIsbn] = useState("");
    const [price, setPrice] = useState("");
    const [quantity, setQuantity] = useState("");
    const [authorId, setAuthorId] = useState("");
    const [publisherId, setPublisherId] = useState("");

    const [authors, setAuthors] = useState([]);
    const [publishers, setPublishers] = useState([]);

    const navigate = useNavigate();

    useEffect(() => {
        axios.get("http://localhost:8080/api/authors").then((res) => setAuthors(res.data));
        axios.get("http://localhost:8080/api/publishers").then((res) => setPublishers(res.data));
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post("http://localhost:8080/api/books", {
                title,
                category,
                isbn,
                price: parseFloat(price),
                quantity: parseInt(quantity),
                authorId: parseInt(authorId),
                publisherId: parseInt(publisherId),
            });
            navigate("/books");
        } catch (err) {
            console.error(err);
            alert("Error creating book");
        }
    };

    return (
        <div className="p-4">
            <h2 className="text-2xl font-bold mb-4">Add Book</h2>
            <form onSubmit={handleSubmit} className="space-y-2 max-w-md">
                <input
                    type="text"
                    placeholder="Title"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    className="border p-2 w-full"
                    required
                />
                <input
                    type="text"
                    placeholder="Category"
                    value={category}
                    onChange={(e) => setCategory(e.target.value)}
                    className="border p-2 w-full"
                    required
                />
                <input
                    type="text"
                    placeholder="ISBN"
                    value={isbn}
                    onChange={(e) => setIsbn(e.target.value)}
                    className="border p-2 w-full"
                    required
                />
                <input
                    type="number"
                    placeholder="Price"
                    value={price}
                    onChange={(e) => setPrice(e.target.value)}
                    className="border p-2 w-full"
                    required
                />
                <input
                    type="number"
                    placeholder="Quantity"
                    value={quantity}
                    onChange={(e) => setQuantity(e.target.value)}
                    className="border p-2 w-full"
                    required
                />
                <select
                    value={authorId}
                    onChange={(e) => setAuthorId(e.target.value)}
                    className="border p-2 w-full"
                    required
                >
                    <option value="">Select Author</option>
                    {authors.map((a) => (
                        <option key={a.id} value={a.id}>
                            {a.name}
                        </option>
                    ))}
                </select>
                <select
                    value={publisherId}
                    onChange={(e) => setPublisherId(e.target.value)}
                    className="border p-2 w-full"
                    required
                >
                    <option value="">Select Publisher</option>
                    {publishers.map((p) => (
                        <option key={p.id} value={p.id}>
                            {p.name}
                        </option>
                    ))}
                </select>
                <button
                    type="submit"
                    className="bg-blue-500 text-white px-4 py-2 rounded"
                >
                    Create Book
                </button>
            </form>
        </div>
    );
}
