import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function AuthorForm() {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post("http://localhost:8080/api/authors", { name, email });
            navigate("/authors");
        } catch (err) {
            console.error(err);
            alert("Error creating author");
        }
    };

    return (
        <div className="p-4">
            <h2 className="text-2xl font-bold mb-4">Add Author</h2>
            <form onSubmit={handleSubmit} className="space-y-2 max-w-md">
                <input
                    type="text"
                    placeholder="Name"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    className="border p-2 w-full"
                    required
                />
                <input
                    type="email"
                    placeholder="Email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    className="border p-2 w-full"
                    required
                />
                <button
                    type="submit"
                    className="bg-blue-500 text-white px-4 py-2 rounded"
                >
                    Create Author
                </button>
            </form>
        </div>
    );
}
